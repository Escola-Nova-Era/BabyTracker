//
//  ProfileItem.swift
//  BabyTracker
//
//  Created by Madu on 7/5/26.
//
import SwiftUI

struct ProfileItem: Identifiable {
    let id: UUID = UUID()
    let title: String
    let value: String
    let icon: String
    let tint: [Color]
    let tintIcon: Color
}
