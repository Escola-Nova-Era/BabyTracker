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
            gradient: AppColors.onboardingGradients[0]
        ),
        OnboardingPage(
            title: .stayOnTopOfEveryRoutine,
            description: .logActivitiesQuicklyAndKeepYourBabysDayOrganized,
            image: .trackIcons,
            gradient: AppColors.onboardingGradients[1]
        ),
        OnboardingPage(
            title: .seePatternsAndGrowWithConfidence,
            description: .understandYourBabysHabitsAndGetHelpfulInsightsOverTime,
            image: .insights,
            gradient: AppColors.onboardingGradients[2]
        )
    ]
    
    var totalPages: Int {
//        pages.count + 1
        pages.count
    }
   
}
