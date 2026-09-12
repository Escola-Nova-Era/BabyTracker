//
//  PrimaryGradientButton.swift
//  BabyTracker
//
//  Created by Gabriel Silva Custodio on 08/07/2026.
//

import SwiftUI

struct PrimaryGradientButton: View {
    let title: String
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            Text(title)
                .font(AppTypography.subheadline)
                .foregroundStyle(.white)
                .frame(maxWidth: .infinity)
                .frame(height: 56)
                .background(
                    LinearGradient(
                        colors: [
                            AppColors.lavander,
                            AppColors.primarySoft
                        ],
                        startPoint: .leading,
                        endPoint: .trailing
                    )
                )
                .clipShape(RoundedRectangle(cornerRadius: AppTheme.cornerRadius, style: .continuous))
                .shadow(color: AppColors.purpleSoft, radius: 14, x: 0, y: 8)
        }
        .buttonStyle(.plain)
    }
}
