import Foundation
import Combine
import SwiftData
import SwiftUI

@MainActor
final class InsightsViewModel: ObservableObject {
    @Published var cards: [InsightsCardData] = []

    private var repository: TrackingRepositoryProtocol?

    func load(context: ModelContext) {
        if repository == nil {
            repository = TrackingRepository(context: context)
        }
        reload()
    }

    func reload() {
        guard let repository else { return }
        let feeding = (try? repository.dailyTotals(kind: .feeding, days: 7)) ?? []
        let diaper = (try? repository.dailyTotals(kind: .diaper, days: 7)) ?? []

        cards = [
            makeFeedingCard(from: feeding),
            makeDiaperCard(from: diaper)
        ]
    }

    private func makeFeedingCard(from totals: [DailyTotal]) -> InsightsCardData {
        let values = totals.map(\.value)
        let peak = values.max() ?? 0
        return InsightsCardData(
            title: "Daily Feeding Quantity",
            iconName: "drop.fill",
            iconColor: AppColors.primary,
            subtitle: "Last 7 Days",
            values: values,
            days: totals.map { DateFormatting.shortWeekday($0.date) },
            yValues: [0, 200, 400, 600, 800, 1000],
            barColor: AppColors.accent,
            summary: peak > 0
                ? "Summary: feeding peaked near \(Int(peak)) ml this week."
                : "Summary: no feedings recorded yet this week."
        )
    }

    private func makeDiaperCard(from totals: [DailyTotal]) -> InsightsCardData {
        let values = totals.map(\.value)
        let total = Int(values.reduce(0, +))
        return InsightsCardData(
            title: "Daily Diaper Changes",
            iconName: "heart.text.square.fill",
            iconColor: AppColors.primary,
            subtitle: "Last 7 Days",
            values: values,
            days: totals.map { DateFormatting.shortWeekday($0.date) },
            yValues: [0, 2, 4, 6, 8, 10],
            barColor: AppColors.accent,
            summary: total > 0
                ? "Summary: \(total) diaper changes over the last 7 days."
                : "Summary: no diaper changes recorded yet this week."
        )
    }
}
