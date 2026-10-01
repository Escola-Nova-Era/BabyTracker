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
            title: .welcomeToBabyCare,
            description: .trackFeedingSleepDiaperChangesAndDailyMomentsWithEase,
                image: .mommyBaby,
            gradient: AppColors.onboardingGradientWelcome
        ),
        OnboardingPage(
            title: .stayOnTopOfEveryRoutine,
            description: .logActivitiesQuicklyAndKeepYourBabysDayOrganized,
            image: .trackIcons,
            gradient: AppColors.onboardingGradientRoutine
        ),
        OnboardingPage(
            title: .seePatternsAndGrowWithConfidence,
            description: .understandYourBabysHabitsAndGetHelpfulInsightsOverTime,
            image: .insights,
            gradient: AppColors.onboardingGradientInsights
        )
    ]
    
    var totalPages: Int {
        pages.count
    }
   
}
