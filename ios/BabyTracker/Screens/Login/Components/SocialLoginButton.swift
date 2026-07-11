//
//  SocialLoginButton.swift
//  BabyTracker
//
//  Created by Gabriel Silva Custodio on 08/07/2026.
//

import SwiftUI

enum SocialLoginButtonStyle {
    case google
    case apple
}

struct SocialLoginButton: View {
    let title: String
    let style: SocialLoginButtonStyle
    let action: () -> Void
    
    private var backgroundColor: Color {
        switch style {
        case .google:
            return .white
        case .apple:
            return .black
        }
    }
    
    private var foregroundColor: Color {
        switch style {
        case .google:
            return Color.black.opacity(0.82)
        case .apple:
            return .white
        }
    }
    
    private var icon: Image {
        switch style {
        case .google:
            return Image("google-logo")
        case .apple:
            return Image(systemName: "apple.logo")
        }
    }
    
    var body: some View {
        Button(action: action) {
            HStack(spacing: 10) {
                if style == .google {
                    icon
                        .resizable()
                        .scaledToFit()
                        .frame(width: 28, height: 28)
                }
                
                if style == .apple {
                    icon
                        .font(AppTypography.iconMedium)
                }
              
                
                Text(title)
                    .font(.system(size: 15, weight: .heavy))
            }
            .foregroundStyle(foregroundColor)
            .frame(maxWidth: .infinity)
            .frame(height: 52)
            .background(
                RoundedRectangle(cornerRadius: AppTheme.cornerRadius, style: .continuous)
                    .fill(backgroundColor)
            )
            .overlay {
                if style == .google {
                    RoundedRectangle(cornerRadius: AppTheme.cornerRadius, style: .continuous)
                        .stroke(Color.gray.opacity(0.22), lineWidth: 1)
                }
            }
        }
        .buttonStyle(.plain)
    }
}
