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
            InitialOnboardingBannerView(image: page.imageName)
            
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
            LinearGradient(
                colors: [
                    AppColors.purpleSoft,
                    AppColors.surface
                ],
                startPoint: .top,
                endPoint: .bottom
            )
        )
    }
}

#Preview {
    OnboardingPageView(
            page: OnboardingPage(
                title: "Welcome to BabyCare",
                description: "Track feeding, sleep, diaper changes, and daily moments with ease",
                imageName: "insights"
            ),
            pageIndex: 0,
            currentPage: 0,
            numberOfPages: 3
        )
}
