import SwiftUI
import Combine

final class InsightsViewModel: ObservableObject {
    @Published var cards: [InsightsCardData] = []
    
    init() {
        cards = makeMockCards()
    }
    
    private func makeMockCards() -> [InsightsCardData] {
        let days = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"]
    
        return [
            InsightsCardData(
                title: "Daily Feeding Quantity",
                iconName: "drop.fill",
                iconColor: AppColors.primary,
                subtitle: "Last 7 Days",
                values: [620, 700, 740, 690, 760, 720, 710].map(Double.init), //Converter int para Double
                days: days,
                yValues: [0, 200, 400, 600, 800, 1000],
                barColor: AppColors.accent,
                summary: "Summary: feeding stayed stable this week, with peak near 760 ml and no abrupt drops"
            ),
            
            InsightsCardData(
                title: "Daily Diaper Changes",
                iconName: "heart.text.square.fill",
                iconColor: AppColors.primary,
                subtitle: "Last 7 Days",
                values: [6, 7, 5, 8, 6, 7, 5].map(Double.init), //Converter int para Double
                days: days,
                yValues: [0, 2, 4, 6, 8, 10],
                barColor: AppColors.accent,
                summary: "Summary: diaper changes remained in the expected 5-8 day range over the week"
            )
        ]
    }
}
