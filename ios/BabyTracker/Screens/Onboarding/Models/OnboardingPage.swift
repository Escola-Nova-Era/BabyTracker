//
//  OnboardingPage.swift
//  BabyTracker
//
//  Created by Ismael Costa on 03/09/26.
//

import Foundation
import DeveloperToolsSupport


struct OnboardingPage: Identifiable{
    let id = UUID()
    let title: LocalizedStringResource
    let description: LocalizedStringResource
    let image: ImageResource
}
