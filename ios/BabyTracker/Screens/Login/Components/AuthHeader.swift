//
//  AuthHeaderView.swift
//  BabyTracker
//
//  Created by Gabriel Silva Custodio on 08/07/2026.
//

import SwiftUI

struct AuthHeader: View {
    var body: some View {
        VStack(spacing: 14) {
            ZStack {
                RoundedRectangle(cornerRadius: AppTheme.cornerRadius, style: .continuous)
                    .fill(
                        LinearGradient(
                            colors: [
                                AppColors.lavander,
                                AppColors.primarySoft
                            ],
                            startPoint: .topLeading,
                            endPoint: .bottomTrailing
                        )
                    )
                    .frame(width: 78, height: 78)
                
                Image(systemName: "figure.child")
                    .font(AppTypography.iconHero)
                    .foregroundStyle(.white)
            }
            .shadow(color: AppColors.purpleSoft, radius: 16, x: 0, y: 8)
            
            VStack(spacing: 6) {
                Text("Login")
                    .font(AppTypography.screenTitle)
                    .foregroundStyle(AppColors.textPrimary)
                
                Text("Please sign in to continue")
                    .font(AppTypography.subheadline)
                    .foregroundStyle(AppColors.textSecondary)
            }
        }
        .frame(maxWidth: .infinity)
    }
}
