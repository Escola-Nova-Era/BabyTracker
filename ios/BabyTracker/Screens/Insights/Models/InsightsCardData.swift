import SwiftUI

struct InsightsCardData: Identifiable {
    let id = UUID()
    let title: String
    let iconName: String
    let iconColor: Color
    let subtitle: String
    let values: [Double]
    let days: [String]
    let yValues: [Int]
    let barColor: Color
    let summary: String
}
