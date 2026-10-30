//
//  DividerWithText.swift
//  BabyTracker
//
//  Created by Gabriel Silva Custodio on 08/07/2026.
//

import SwiftUI

struct DividerWithText: View {
    let text: String
    
    var body: some View {
        HStack(spacing: 14) {
            Rectangle()
                .fill(AppColors.divider)
                .frame(height: 1)
            
            Text(text)
                .font(AppTypography.overline)
                .foregroundStyle(AppColors.textSecondary)
                .lineLimit(1)
            
            Rectangle()
                .fill(AppColors.divider)
                .frame(height: 1)
        }
        .padding(.vertical, 2)
    }
}
