import SwiftUI

struct ProfileItem: Identifiable {
    let id: UUID = UUID()
    let title: String
    let value: String
    let icon: String
    let tint: [Color]
    let tintIcon: Color
}
