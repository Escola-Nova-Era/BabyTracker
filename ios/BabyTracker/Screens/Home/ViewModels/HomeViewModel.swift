import Foundation
import Combine
import SwiftData
import SwiftUI

@MainActor
final class HomeViewModel: ObservableObject {

    // Cabeçalho (perfil do bebê)
    @Published private(set) var babyName: String = "Your Baby"
    @Published private(set) var babySubtitle: String = "Add your baby's details"

    @Published private(set) var summaryItems: [SummaryItem] = []

    // Cada botão abre o Quick Action do seu tipo.
    @Published private(set) var actionButtons: [ActionButton] = [
        ActionButton(title: "Add Feeding", icon: "drop.fill", colors: Color.indigo, kind: .feeding),
        ActionButton(title: "Add Diaper", icon: "heart.text.square.fill", colors: Color.green, kind: .diaper),
        ActionButton(title: "Sleep/Wake", icon: "moon.fill", colors: Color.purple, kind: .sleep),
        ActionButton(title: "Add Bath", icon: "bathtub.fill", colors: Color.orange, kind: .bath)
    ]

    private var trackingRepository: TrackingRepositoryProtocol?
    private var babyRepository: BabyRepositoryProtocol?

    func load(context: ModelContext) {
        if trackingRepository == nil {
            trackingRepository = TrackingRepository(context: context)
            babyRepository = BabyRepository(context: context)
        }
        reload()
    }

    // Chamar após cada novo registro.
    func reload() {
        reloadBaby()
        reloadSummary()
    }

    private func reloadBaby() {
        guard let babyRepository,
              let baby = (try? babyRepository.currentBaby()) ?? nil else { return }
        babyName = baby.name

        var parts: [String] = []
        if let days = baby.ageInDays { parts.append("\(days) days old") }
        if let weight = baby.weightKg { parts.append("\(weight.formatted(.number.precision(.fractionLength(0...1)))) kg") }
        babySubtitle = parts.isEmpty ? "Welcome! 👶" : parts.joined(separator: " • ")
    }

    private func reloadSummary() {
        guard let repository = trackingRepository else { return }

        // `try?` sobre retorno opcional gera opcional-duplo; o `?? nil` achata para TrackingEvent?.
        let feeding = (try? repository.lastEvent(of: .feeding)) ?? nil
        let diaper = (try? repository.lastEvent(of: .diaper)) ?? nil
        let sleep = (try? repository.lastEvent(of: .sleep)) ?? nil
        let bath = (try? repository.lastEvent(of: .bath)) ?? nil

        summaryItems = [
            makeSummary(
                title: "Last Feeding",
                event: feeding,
                detail: feeding?.amountMl.map { "\($0) ml" } ?? "No data",
                icon: "drop.fill",
                tint: [AppColors.blueSoft, AppColors.surface],
                tintIcon: .blue
            ),
            makeSummary(
                title: "Last Diaper",
                event: diaper,
                detail: diaper?.diaperKind?.label ?? "No data",
                icon: "heart.text.square.fill",
                tint: [AppColors.greenSoft, AppColors.surface],
                tintIcon: .green
            ),
            makeSleepSummary(event: sleep),
            makeSummary(
                title: "Last Bath",
                event: bath,
                detail: bath.map { DateFormatting.clockTime($0.date) } ?? "No data",
                icon: "bathtub.fill",
                tint: [AppColors.orangeSoft, AppColors.surface],
                tintIcon: .orange
            )
        ]
    }

    private func makeSummary(
        title: String,
        event: TrackingEvent?,
        detail: String,
        icon: String,
        tint: [Color],
        tintIcon: Color
    ) -> SummaryItem {
        let value = event.map { DateFormatting.relativeShort(from: $0.date) } ?? "—"
        return SummaryItem(title: title, value: value, detail: detail, icon: icon, tint: tint, tintIcon: tintIcon)
    }

    private func makeSleepSummary(event: TrackingEvent?) -> SummaryItem {
        let value: String
        let detail: String
        if let event {
            value = event.sleepState == .fellAsleep ? "Asleep" : "Awake"
            detail = "Since \(DateFormatting.relativeShort(from: event.date))"
        } else {
            value = "—"
            detail = "No data"
        }
        return SummaryItem(
            title: "Sleep Status",
            value: value,
            detail: detail,
            icon: "moon.fill",
            tint: [AppColors.purpleSoft, AppColors.surface],
            tintIcon: .purple
        )
    }
}
