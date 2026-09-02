import Foundation
import Combine
import SwiftData
import SwiftUI

@MainActor
final class TrackingViewModel: ObservableObject {

    /// Eventos de hoje, do mais recente para o mais antigo (timeline).
    @Published private(set) var trackingItems: [TrackingItem] = []

    private var repository: TrackingRepositoryProtocol?

    /// Data de hoje formatada para o cabeçalho (ex.: "January 26, 2026").
    var todayLabel: String {
        Date.now.formatted(.dateTime.month(.wide).day().year())
    }

    func load(context: ModelContext) {
        if repository == nil {
            repository = TrackingRepository(context: context)
        }
        reload()
    }

    func reload() {
        guard let repository else { return }
        let events = (try? repository.todaysEvents()) ?? []
        trackingItems = events.map { event in
            TrackingItem(
                id: event.id,
                title: event.displayTitle,
                detail: event.detailText,
                time: DateFormatting.clockTime(event.date),
                icon: event.displayIcon,
                tint: event.displayTint
            )
        }
    }
}
