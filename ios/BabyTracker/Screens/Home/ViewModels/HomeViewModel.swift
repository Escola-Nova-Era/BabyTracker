import Foundation
import Combine
import SwiftUI

final class HomeViewModel: ObservableObject {
    // Lembrete: Alterar para @Publisehd private(set) var quando esses dados passarem a mudar.
    @Published private(set) var summaryItems: [SummaryItem] = [
        SummaryItem(title: "Last Feeding", value: "2h ago", detail: "120 ml", icon: "drop.fill", tint: [AppColors.blueSoft, AppColors.surface], tintIcon: Color.blue),
        SummaryItem(title: "Last Diaper", value: "45m ago", detail: "Pee", icon: "heart.text.square.fill", tint: [AppColors.greenSoft, AppColors.surface], tintIcon: Color.green),
        SummaryItem(title: "Sleep Status", value: "Awake", detail: "Since 20m", icon: "moon.fill", tint: [AppColors.purpleSoft, AppColors.surface], tintIcon: Color.purple),
        SummaryItem(title: "Last Bath", value: "Yesterday", detail: "7:30 PM", icon: "bathtub.fill", tint: [AppColors.orangeSoft, AppColors.surface], tintIcon: Color.orange)
        
        ]
    
    @Published private(set) var actionButtons: [ActionButton] = [
        ActionButton(title: "Add Feeding", icon: "drop.fill", colors: Color.indigo),
        ActionButton(title: "Add Diaper", icon: "heart.text.square.fill", colors: Color.green),
        ActionButton(title: "Sleep/Wake", icon: "moon.fill", colors: Color.purple),
        ActionButton(title: "Add Bath", icon: "bathtub.fill", colors: Color.orange)
        ]
}
