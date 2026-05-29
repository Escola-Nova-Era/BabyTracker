import SwiftUI
import Foundation

struct TrackingItem: Identifiable {
    let id = UUID()
    let title: String
    let detail: String
    let time: String
    let icon: String
    let tint: Color
}
