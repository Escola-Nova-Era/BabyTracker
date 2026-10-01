//
//  OnboardingTitleView.swift
//  BabyTracker
//
//  Created by Ismael Costa on 09/09/26.
//

import SwiftUI

struct OnboardingTitleView: View {
    let title: LocalizedStringResource
    
    var body: some View {
        Text(title)
            .font(AppTypography.largeTitle)
            .multilineTextAlignment(.center)
            .padding(.horizontal, AppSpacing.xxLarge)

    }
}
