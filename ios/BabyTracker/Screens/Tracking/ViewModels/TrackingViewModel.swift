import Foundation
import Combine
import SwiftUI

final class TrackingViewModel: ObservableObject {
    
    @Published private(set) var trackingItems: [TrackingItem] = [
        TrackingItem(title: "Bottle Feeding", detail: "120 ml • 15 min", time: "2:30 PM", icon: "drop.fill", tint: AppColors.highlight),
        TrackingItem(title: "Woke Up", detail: "Slept for 2h 15min", time: "2:00 PM", icon: "sun.max.fill", tint: AppColors.accent),
        TrackingItem(title: "Diaper Change", detail: "Pee", time: "1:45 PM", icon: "heart.text.square.fill", tint: AppColors.primarySoft),
        TrackingItem(title: "Fell Asleep", detail: "Nap time", time: "11:45 AM", icon: "moon.stars", tint: AppColors.accent),
        TrackingItem(title: "Breastfeeding", detail: "Left side • 20 min", time: "11:15 AM", icon: "heart.fill", tint: AppColors.mint),
        TrackingItem(title: "Bath Time", detail: "Warm bath", time: "10:00 AM", icon: "bathtub.fill", tint: AppColors.highlight),
        TrackingItem(title: "Diaper Change", detail: "Poop", time: "9:30 AM", icon: "heart.text.square.fill", tint: AppColors.primarySoft)
    ]
}
