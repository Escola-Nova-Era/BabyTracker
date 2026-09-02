import Foundation
import SwiftData
import SwiftUI

// MARK: - Domínio dos eventos

// Tipo de evento. Persistimos o `rawValue` (via `kindRaw`) porque `#Predicate`
// filtra por String simples, mas não por propriedades computadas.
enum TrackingKind: String, Codable, CaseIterable, Identifiable {
    case feeding
    case diaper
    case sleep
    case bath

    var id: String { rawValue }

    var label: String {
        switch self {
        case .feeding: "Feeding"
        case .diaper: "Diaper"
        case .sleep: "Sleep/Wake"
        case .bath: "Bath"
        }
    }

    var icon: String {
        switch self {
        case .feeding: "drop.fill"
        case .diaper: "heart.text.square.fill"
        case .sleep: "moon.fill"
        case .bath: "bathtub.fill"
        }
    }

    var tint: Color {
        switch self {
        case .feeding: AppColors.highlight
        case .diaper: AppColors.primarySoft
        case .sleep: AppColors.accent
        case .bath: AppColors.mint
        }
    }
}

enum DiaperKind: String, Codable, CaseIterable, Identifiable {
    case pee
    case poop
    case both

    var id: String { rawValue }

    var label: String {
        switch self {
        case .pee: "Pee"
        case .poop: "Poop"
        case .both: "Pee & Poop"
        }
    }
}

enum SleepState: String, Codable, CaseIterable, Identifiable {
    case fellAsleep
    case wokeUp

    var id: String { rawValue }

    var label: String {
        switch self {
        case .fellAsleep: "Fell Asleep"
        case .wokeUp: "Woke Up"
        }
    }

    var icon: String {
        switch self {
        case .fellAsleep: "moon.stars"
        case .wokeUp: "sun.max.fill"
        }
    }
}

// MARK: - Modelo persistido

/// Modelo único para todos os tipos de evento (o timeline mistura os tipos
/// cronologicamente, o que simplifica as queries). Campos específicos são opcionais.
@Model
final class TrackingEvent {
    var id: UUID
    var date: Date
    var kindRaw: String

    // Feeding
    var amountMl: Int?
    var durationMin: Int?

    // Diaper
    var diaperKindRaw: String?

    // Sleep
    var sleepStateRaw: String?

    var note: String?

    init(
        id: UUID = UUID(),
        date: Date,
        kind: TrackingKind,
        amountMl: Int? = nil,
        durationMin: Int? = nil,
        diaperKind: DiaperKind? = nil,
        sleepState: SleepState? = nil,
        note: String? = nil
    ) {
        self.id = id
        self.date = date
        self.kindRaw = kind.rawValue
        self.amountMl = amountMl
        self.durationMin = durationMin
        self.diaperKindRaw = diaperKind?.rawValue
        self.sleepStateRaw = sleepState?.rawValue
        self.note = note
    }

    // Acesso tipado — não usar em #Predicate (são computed).
    var kind: TrackingKind { TrackingKind(rawValue: kindRaw) ?? .feeding }
    var diaperKind: DiaperKind? { diaperKindRaw.flatMap(DiaperKind.init) }
    var sleepState: SleepState? { sleepStateRaw.flatMap(SleepState.init) }

    var displayIcon: String {
        if kind == .sleep, let sleepState { return sleepState.icon }
        return kind.icon
    }

    var displayTint: Color {
        if kind == .sleep, sleepState == .wokeUp { return AppColors.accent }
        return kind.tint
    }

    var displayTitle: String {
        switch kind {
        case .feeding: "Bottle Feeding"
        case .diaper: "Diaper Change"
        case .sleep: sleepState?.label ?? "Sleep"
        case .bath: "Bath Time"
        }
    }

    var detailText: String {
        switch kind {
        case .feeding:
            var parts: [String] = []
            if let amountMl { parts.append("\(amountMl) ml") }
            if let durationMin { parts.append("\(durationMin) min") }
            return parts.isEmpty ? (note ?? "Feeding") : parts.joined(separator: " • ")
        case .diaper:
            return diaperKind?.label ?? "Diaper"
        case .sleep:
            return note ?? (sleepState?.label ?? "Sleep")
        case .bath:
            return note ?? "Warm bath"
        }
    }
}
