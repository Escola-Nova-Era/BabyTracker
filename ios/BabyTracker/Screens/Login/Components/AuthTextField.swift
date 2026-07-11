//
//  AuthTextField.swift
//  BabyTracker
//
//  Created by Gabriel Silva Custodio on 08/07/2026.
//

import SwiftUI

struct AuthTextField: View {
    let title: String
    let placeholder: String
    let systemImage: String
    @Binding var text: String
    
    var keyboardType: UIKeyboardType = .default
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(title)
                .font(AppTypography.bodyStrong)
                .foregroundStyle(AppColors.textPrimary)
            
            HStack(spacing: 10) {
                Image(systemName: systemImage)
                    .font(AppTypography.iconSmall)
                    .foregroundStyle(AppColors.textTertiary)
                    .frame(width: 18)
                
                TextField(placeholder, text: $text)
                    .keyboardType(keyboardType)
                    .textInputAutocapitalization(keyboardType == .emailAddress ? .never : .words)
                    .autocorrectionDisabled(keyboardType == .emailAddress)
                    .font(AppTypography.body)
                    .foregroundStyle(AppColors.textTertiary)
            }
            .padding(.horizontal, 14)
            .frame(height: 54)
            .background(
                RoundedRectangle(cornerRadius: AppTheme.cornerRadius, style: .continuous)
                    .fill(Color.gray.opacity(0.055))
            )
            .overlay(
                RoundedRectangle(cornerRadius: AppTheme.cornerRadius, style: .continuous)
                    .stroke(Color.gray.opacity(0.16), lineWidth: 1)
            )
        }
    }
}
