import Foundation
import Combine
import SwiftData
import SwiftUI

@MainActor
final class ProfileViewModel: ObservableObject {

    // Dados do bebê (derivados do modelo Baby persistido)
    @Published private(set) var babyName: String = "Your Baby"
    @Published private(set) var bornLabel: String = "Add birth date"
    @Published private(set) var ageLabel: String = "—"
    @Published private(set) var weightLabel: String = "—"
    @Published private(set) var heightLabel: String = "—"

    private var repository: BabyRepositoryProtocol?

    func load(context: ModelContext) {
        if repository == nil {
            repository = BabyRepository(context: context)
        }
        reload()
    }

    func reload() {
        guard let repository,
              let baby = (try? repository.currentBaby()) ?? nil else { return }

        babyName = baby.name

        if let birthDate = baby.birthDate {
            bornLabel = "Born \(birthDate.formatted(.dateTime.month(.wide).day().year()))"
        }
        if let weeks = baby.ageInWeeks {
            ageLabel = "\(weeks) weeks"
        }
        if let weight = baby.weightKg {
            weightLabel = "\(weight.formatted(.number.precision(.fractionLength(0...2)))) kg"
        }
        if let height = baby.heightCm {
            heightLabel = "\(height.formatted(.number.precision(.fractionLength(0...0)))) cm"
        }
    }

    @Published private(set) var settingItems: [SettingsItem] = [
        SettingsItem(
            title: "Edit Baby's profile",
            detail: "Update name, birth date and info",
            icon: "pencil",
            tint:  [ Color.orange, AppColors.orangeMuted],
            tintIcon: AppColors.surface
        ),

        SettingsItem(
            title: "Notifications",
            detail: "Feeding and sleep reminders",
            icon: "bell.fill",
            tint: [ Color.blue, AppColors.blueMuted],
            tintIcon: AppColors.surface
        ),

        SettingsItem(
            title: "Export Data",
            detail: "Download tracking history",
            icon: "square.and.arrow.down.fill",
            tint: [ Color.green, AppColors.greenMuted],
            tintIcon: AppColors.surface
        ),

        SettingsItem(
            title: "Help & Support",
            detail: "Get help and contact support",
            icon: "questionmark.square",
            tint: [ Color.purple, AppColors.purpleMuted],
            tintIcon: AppColors.surface
        )
    ]

    func handleTap(item: SettingsItem){
        print (item.title)
        //navegation
    }
}
