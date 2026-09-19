//
//  OnboardingPageIndicatorView.swift
//  BabyTracker
//
//  Created by Ismael Costa on 09/09/26.
//

import SwiftUI

struct OnboardingPageIndicatorView: View {
    let numberOfPages: Int
    let currentPage: Int
    
    var body: some View {
        HStack(spacing: 12) {
            ForEach(0..<numberOfPages, id: \.self) { index in
                    Circle()
                        .fill(
                            index == currentPage
                                ? AppColors.dotPurple
                                : AppColors.dotLight.opacity(0.3)
                        )
                        .frame(width: 10, height: 10)
                }
        }

    }
}
