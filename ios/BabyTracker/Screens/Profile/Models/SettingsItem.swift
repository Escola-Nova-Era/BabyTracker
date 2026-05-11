//
//  SettingsItem.swift
//  BabyTracker
//
//  Created by Madu on 1/5/26.
//
import SwiftUI

struct SettingsItem: Identifiable {
    let id: UUID = UUID()
    let title: String
    let detail: String
    let icon: String
    let tint: [Color]
    let tintIcon: Color
}


/*#Preview {
    SettingsItem()
}*/
