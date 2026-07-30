//
//  PasswordTextField 2.swift
//  BabyTracker
//
//  Created by Gabriel Silva Custodio on 08/07/2026.
//

import SwiftUI



struct PasswordTextField: View {
    let title: String
    let placeholder: String
    @Binding var text: String
    
    @State private var isPasswordVisible: Bool = false
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(title)
                .font(AppTypography.bodyStrong)
                .foregroundStyle(AppColors.textPrimary)
            
            HStack(spacing: 10) {
                Image(systemName: "lock")
                    .font(AppTypography.iconSmall)
                    .foregroundStyle(AppColors.textTertiary)
                    .frame(width: 18)
                
                Group {
                    if isPasswordVisible {
                        TextField(placeholder, text: $text)
                    } else {
                        SecureField(placeholder, text: $text)
                    }
                }
                .font(AppTypography.body)
                .foregroundStyle(AppColors.textTertiary)
                
                Button {
                    isPasswordVisible.toggle()
                } label: {
                    Image(systemName: isPasswordVisible ? "eye.slash" : "eye")
                        .font(AppTypography.iconSmall)
                        .foregroundStyle(AppColors.textSecondary)
                }
                .buttonStyle(.plain)
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
