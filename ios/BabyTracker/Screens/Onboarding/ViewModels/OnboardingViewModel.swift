//
//  OnboardingViewModel.swift
//  BabyTracker
//
//  Created by Ismael Costa on 09/09/26.
//

import Foundation

@Observable
final class OnboardingViewModel {
    var currentPage = 0;
    
    let pages = [
        OnboardingPage(
                title: "Welcome to BabyCare",
                description: "Track feeding, sleep, diaper changes, and daily moments with ease.",
                imageName: "mommyBaby"
        ),
        OnboardingPage(
                title: "Stay on top of every routine",
                description: "Log activities quickly and keep your baby's day organized.",
                imageName: "trackIcons"
        ),
        OnboardingPage(
                title: "See patterns and grow with confidence",
                description: "Understand your baby's habits and get helpful insights over time.",
                imageName: "insights"
        )
    ]
}
