//
//  OnboardingPageView.swift
//  BabyTracker
//
//  Created by Ismael Costa on 03/09/26.
//

import SwiftUI

struct OnboardingPageView: View {
    let page: OnboardingPage
    let pageIndex: Int
    let currentPage: Int
    let numberOfPages: Int
    
    var body: some View {
        
        VStack(spacing: 0){
            Spacer()
            
            // MARK: - Image
            InitialOnboardingBannerView(image: page.image)
            
            Spacer()
                .frame(height: AppSpacing.xxxLarge)
            
            OnboardingTitleView(title: page.title)
            
            Spacer()
                .frame(height: AppSpacing.medium)

            // MARK: - Description
            OnboardingDescriptionTextView(description: page.description)

            Spacer()

            // MARK: - Page indicator
            OnboardingPageIndicatorView(numberOfPages: numberOfPages, currentPage: currentPage)

            Spacer()
                .frame(height: AppSpacing.xxxLarge)
        }
        .frame(maxWidth: .infinity)
        .background(
            ZStack {
                let gradient = AppColors.onboardingGradients[pageIndex]
                // Gradiente base com 3 cores
                LinearGradient(
                    stops: [
                        .init(color: gradient.start, location: 0.0),
                        .init(color: gradient.mid, location: 0.55),
                        .init(color: gradient.end, location: 1.0)
                    ],
                    startPoint: .top,
                    endPoint: .bottom
                )

                // Glow superior
                RadialGradient(
                    colors: [
                        gradient.start.opacity(0.45),
                        gradient.mid.opacity(0.20),
                        Color.clear
                    ],
                    center: UnitPoint(x: 0.5, y: 0.18),
                    startRadius: 0,
                    endRadius: 330
                )
            }
            .ignoresSafeArea()
        )
    }
}

#Preview {
    OnboardingPageView(
            page: OnboardingPage(
                title: "Welcome to BabyCare",
                description: "Track feeding, sleep, diaper changes, and daily moments with ease",
                image: .mommyBaby
            ),
            pageIndex: 0,
            currentPage: 0,
            numberOfPages: 4
        )
}
