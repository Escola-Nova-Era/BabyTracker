//
//  ProfileViewModel.swift
//  BabyTracker
//
//  Created by Madu on 1/5/26.
//
import Foundation
import Combine
import SwiftUI

final class ProfileViewModel: ObservableObject {
    
    // Dados do Bebê
    @Published var babyName: String = "Emma Rose"
    @Published var babyBirthDate: String = "Born March 15, 2024"
    @Published var babyImageName: String = "person.fill"
    
    // Lembrete: Alterar para @Publisehd private(set) var quando esses dados passarem a mudar.
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
    
    @Published private(set) var profileItems: [ProfileItem] = [
        ProfileItem(
            title: "AGE",
            value: "11 weeks",
            icon: "calendar",
            tint: [AppColors.blueMuted, AppColors.purpleMuted],
            tintIcon: .blue
        ),
        ProfileItem(
            title: "WEIGHT",
            value: "3.65 kg",
            icon: "scalemass.fill",
            tint: [AppColors.blueMuted, AppColors.purpleMuted],
            tintIcon: .green
        ),
        ProfileItem(
            title: "HEIGHT",
            value: "58 cm",
            icon: "ruler.fill",
            tint: [AppColors.blueMuted, AppColors.purpleMuted],
            tintIcon: .orange
        )
    ]

}
