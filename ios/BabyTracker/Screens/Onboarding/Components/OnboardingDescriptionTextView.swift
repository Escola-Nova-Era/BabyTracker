//
//  OnboardingDescriptionTextView.swift
//  BabyTracker
//
//  Created by Ismael Costa on 09/09/26.
//

import SwiftUI

struct OnboardingDescriptionTextView: View {
    let description: LocalizedStringResource;
    
    var body: some View {
        Text(description)
            .font(AppTypography.body)
            .foregroundStyle(.secondary)
            .multilineTextAlignment(.center)
            .lineSpacing(6)
            .padding(.horizontal, AppSpacing.xxLarge)

    }
}
