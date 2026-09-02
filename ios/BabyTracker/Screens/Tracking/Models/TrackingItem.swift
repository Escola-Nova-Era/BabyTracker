import SwiftUI
import Foundation

struct TrackingItem: Identifiable {
    let id: UUID
    let title: String
    let detail: String
    let time: String
    let icon: String
    let tint: Color

    init(
        id: UUID = UUID(),
        title: String,
        detail: String,
        time: String,
        icon: String,
        tint: Color
    ) {
        self.id = id
        self.title = title
        self.detail = detail
        self.time = time
        self.icon = icon
        self.tint = tint
    }
}
