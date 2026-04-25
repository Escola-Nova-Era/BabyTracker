import SwiftUI

struct SummaryItem: Identifiable {
    let id = UUID()
    let title: String
    let value: String
    let detail: String
    let icon: String
    let tint: [Color]
    let tintIcon: Color
}
