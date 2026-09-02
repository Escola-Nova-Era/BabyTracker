import Foundation
import SwiftData

// Total de um dia, usado nas agregações da tela de Insights.
struct DailyTotal: Identifiable {
    let id = UUID()
    let date: Date
    let value: Double
}

// Centralizar o acesso ao ModelContext aqui mantém o MVVM e deixa os ViewModels testáveis.
protocol TrackingRepositoryProtocol {
    func add(_ event: TrackingEvent) throws
    func delete(_ event: TrackingEvent) throws
    func todaysEvents() throws -> [TrackingEvent]
    func lastEvent(of kind: TrackingKind) throws -> TrackingEvent?

    // Total por dia nos últimos `days` dias: feeding soma `amountMl`, os demais contam ocorrências.
    func dailyTotals(kind: TrackingKind, days: Int) throws -> [DailyTotal]
}

struct TrackingRepository: TrackingRepositoryProtocol {
    private let context: ModelContext

    init(context: ModelContext) {
        self.context = context
    }

    func add(_ event: TrackingEvent) throws {
        context.insert(event)
        try context.save()
    }

    func delete(_ event: TrackingEvent) throws {
        context.delete(event)
        try context.save()
    }

    func todaysEvents() throws -> [TrackingEvent] {
        let startOfToday = Calendar.current.startOfDay(for: .now)
        let descriptor = FetchDescriptor<TrackingEvent>(
            predicate: #Predicate { $0.date >= startOfToday },
            sortBy: [SortDescriptor(\.date, order: .reverse)]
        )
        return try context.fetch(descriptor)
    }

    func lastEvent(of kind: TrackingKind) throws -> TrackingEvent? {
        let raw = kind.rawValue
        var descriptor = FetchDescriptor<TrackingEvent>(
            predicate: #Predicate { $0.kindRaw == raw },
            sortBy: [SortDescriptor(\.date, order: .reverse)]
        )
        descriptor.fetchLimit = 1
        return try context.fetch(descriptor).first
    }

    func dailyTotals(kind: TrackingKind, days: Int) throws -> [DailyTotal] {
        let calendar = Calendar.current
        let today = calendar.startOfDay(for: .now)
        // Início da janela: `days - 1` dias atrás (inclui hoje).
        guard let windowStart = calendar.date(byAdding: .day, value: -(days - 1), to: today) else {
            return []
        }

        let raw = kind.rawValue
        let descriptor = FetchDescriptor<TrackingEvent>(
            predicate: #Predicate { $0.kindRaw == raw && $0.date >= windowStart }
        )
        let events = try context.fetch(descriptor)
        let grouped = Dictionary(grouping: events) { calendar.startOfDay(for: $0.date) }

        // Um DailyTotal para cada dia da janela (dias sem eventos viram zero).
        return (0..<days).compactMap { offset -> DailyTotal? in
            guard let day = calendar.date(byAdding: .day, value: offset, to: windowStart) else {
                return nil
            }
            let dayEvents = grouped[day] ?? []
            let value: Double
            if kind == .feeding {
                value = dayEvents.reduce(0) { $0 + Double($1.amountMl ?? 0) }
            } else {
                value = Double(dayEvents.count)
            }
            return DailyTotal(date: day, value: value)
        }
    }
}
