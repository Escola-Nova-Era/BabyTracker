//
//  SettingsCard.swift
//  BabyTracker
//
//  Created by Madu on 1/5/26.
//

import SwiftUI

struct SettingsCard: View {
    let item: SettingsItem
    var action: () -> Void
    
    var body: some View {
        Button(action: action){
            HStack(spacing: AppSpacing.xSmall){
                Image(systemName: item.icon)
                    .font(.system(size: 18))
                    .foregroundColor(AppColors.surface)
                    .frame(width: 30, height: 30)
                    .background {
                        LinearGradient(
                            colors: item.tint,
                            startPoint: .topLeading,
                            endPoint: .bottomTrailing
                        )
                    }
                    .cornerRadius(8)
                VStack(alignment: .leading, spacing: 2) {
                    Text(item.title)
                        .font(.system(size: 16, weight: .semibold))
                        .foregroundColor(AppColors.textPrimary)
                    
                    Text(item.detail)
                        .font(.system(size: 13))
                        .foregroundColor(AppColors.textSecondary)
                }
                
                Spacer()
                Image(systemName: "chevron.forward")
                    .font(.system(size: 14, weight: .semibold))
                    .foregroundColor(AppColors.textSecondary)
            }
            .padding(.vertical, 12)
            .contentShape(Rectangle())
        }
        .buttonStyle(PlainButtonStyle())
    }
}

