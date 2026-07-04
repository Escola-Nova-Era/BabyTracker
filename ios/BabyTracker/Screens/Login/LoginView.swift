//
//  LoginView.swift
//  BabyTracker
//
//  Created by Gabriel Silva Custodio on 17/06/2026.
//


import SwiftUI

// MARK: - Login View

struct LoginView: View {
    @ObservedObject var viewModel: AuthenticationViewModel
    
    var body: some View {
        ZStack {
            LinearGradient(
                colors: [
                    Color.pink.opacity(0.16),
                    Color.purple.opacity(0.10),
                    Color.blue.opacity(0.08)
                ],
                startPoint: .leading,
                endPoint: .trailing
            )
            .ignoresSafeArea()
            
            VStack (spacing: 22) {
                
                AuthHeader()
                
                ScrollView(showsIndicators: false) {
                    VStack(spacing: 22) {
                        
                        VStack(spacing: 16) {
                            
                            AuthTextField(
                                title: "Email",
                                placeholder: "your@email.com",
                                systemImage: "envelope",
                                text: $viewModel.signInEmail,
                                keyboardType: .emailAddress
                            )
                            
                            PasswordTextField(
                                title: "Password",
                                placeholder: "password",
                                text: $viewModel.signInPassword
                            )
                            
                            
                            PrimaryGradientButton(title: "Login") {
                                viewModel.signIn()
                            }
                            
                            DividerWithText(text: "OR SIGN IN WITH")
                            
                            SocialLoginButton(
                                title: "Continue with Google",
                                iconText: "G",
                                style: .google
                            ) {
                                viewModel.continueWithGoogle()
                            }
                            
                            SocialLoginButton(
                                title: "Continue with Apple",
                                systemImage: "apple.logo",
                                style: .apple
                            ) {
                                viewModel.continueWithApple()
                            }
                        }
                        .padding(.horizontal, 22)
                        .padding(.top, 28)
                        .padding(.bottom, 24)
                        .background(
                            RoundedRectangle(cornerRadius: 30, style: .continuous)
                                .fill(Color.white)
                        )
                        .padding(.horizontal, 0)
                    }
                    .padding(.top, 36)
                    .padding(.bottom, 24)
                }
            }
            
            
            
        }
    }
}

// MARK: - Auth Header

struct AuthHeader: View {
    var body: some View {
        VStack(spacing: 14) {
            ZStack {
                RoundedRectangle(cornerRadius: 18, style: .continuous)
                    .fill(
                        LinearGradient(
                            colors: [
                                Color.purple.opacity(0.75),
                                Color.cyan.opacity(0.75)
                            ],
                            startPoint: .topLeading,
                            endPoint: .bottomTrailing
                        )
                    )
                    .frame(width: 78, height: 78)
                
                Image(systemName: "figure.child")
                    .font(.system(size: 32, weight: .semibold))
                    .foregroundStyle(.white)
            }
            .shadow(color: .purple.opacity(0.18), radius: 16, x: 0, y: 8)
            
            VStack(spacing: 6) {
                Text("Login")
                    .font(.system(size: 24, weight: .heavy, design: .rounded))
                    .foregroundStyle(Color.black.opacity(0.88))
                
                Text("Please sign in to continue")
                    .font(.system(size: 13, weight: .medium))
                    .foregroundStyle(Color.gray)
            }
        }
        .frame(maxWidth: .infinity)
    }
}

// MARK: - Auth Text Field

struct AuthTextField: View {
    let title: String
    let placeholder: String
    let systemImage: String
    @Binding var text: String
    
    var keyboardType: UIKeyboardType = .default
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(title)
                .font(.system(size: 13, weight: .semibold))
                .foregroundStyle(Color.black.opacity(0.72))
            
            HStack(spacing: 10) {
                Image(systemName: systemImage)
                    .font(.system(size: 14, weight: .medium))
                    .foregroundStyle(Color.gray.opacity(0.65))
                    .frame(width: 18)
                
                TextField(placeholder, text: $text)
                    .keyboardType(keyboardType)
                    .textInputAutocapitalization(keyboardType == .emailAddress ? .never : .words)
                    .autocorrectionDisabled(keyboardType == .emailAddress)
                    .font(.system(size: 15))
                    .foregroundStyle(Color.black.opacity(0.85))
            }
            .padding(.horizontal, 14)
            .frame(height: 54)
            .background(
                RoundedRectangle(cornerRadius: 14, style: .continuous)
                    .fill(Color.gray.opacity(0.055))
            )
            .overlay(
                RoundedRectangle(cornerRadius: 14, style: .continuous)
                    .stroke(Color.gray.opacity(0.16), lineWidth: 1)
            )
        }
    }
}

// MARK: - Password Text Field

struct PasswordTextField: View {
    let title: String
    let placeholder: String
    @Binding var text: String
    
    @State private var isPasswordVisible: Bool = false
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(title)
                .font(.system(size: 13, weight: .semibold))
                .foregroundStyle(Color.black.opacity(0.72))
            
            HStack(spacing: 10) {
                Image(systemName: "lock")
                    .font(.system(size: 14, weight: .medium))
                    .foregroundStyle(Color.gray.opacity(0.65))
                    .frame(width: 18)
                
                Group {
                    if isPasswordVisible {
                        TextField(placeholder, text: $text)
                    } else {
                        SecureField(placeholder, text: $text)
                    }
                }
                .font(.system(size: 15))
                .foregroundStyle(Color.black.opacity(0.85))
                
                Button {
                    isPasswordVisible.toggle()
                } label: {
                    Image(systemName: isPasswordVisible ? "eye.slash" : "eye")
                        .font(.system(size: 14, weight: .medium))
                        .foregroundStyle(Color.gray.opacity(0.65))
                }
                .buttonStyle(.plain)
            }
            .padding(.horizontal, 14)
            .frame(height: 54)
            .background(
                RoundedRectangle(cornerRadius: 14, style: .continuous)
                    .fill(Color.gray.opacity(0.055))
            )
            .overlay(
                RoundedRectangle(cornerRadius: 14, style: .continuous)
                    .stroke(Color.gray.opacity(0.16), lineWidth: 1)
            )
        }
    }
}

// MARK: - Primary Gradient Button

struct PrimaryGradientButton: View {
    let title: String
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            Text(title)
                .font(.system(size: 15, weight: .heavy))
                .foregroundStyle(.white)
                .frame(maxWidth: .infinity)
                .frame(height: 56)
                .background(
                    LinearGradient(
                        colors: [
                            Color.purple.opacity(0.72),
                            Color.cyan.opacity(0.70)
                        ],
                        startPoint: .leading,
                        endPoint: .trailing
                    )
                )
                .clipShape(RoundedRectangle(cornerRadius: 14, style: .continuous))
                .shadow(color: Color.purple.opacity(0.18), radius: 14, x: 0, y: 8)
        }
        .buttonStyle(.plain)
    }
}

// MARK: - Social Login Button

enum SocialLoginButtonStyle {
    case google
    case apple
}

struct SocialLoginButton: View {
    let title: String
    var systemImage: String?
    var iconText: String?
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
    
    var body: some View {
        Button(action: action) {
            HStack(spacing: 10) {
                if let systemImage {
                    Image(systemName: systemImage)
                        .font(.system(size: 16, weight: .semibold))
                }
                
                if let iconText {
                    Text(iconText)
                        .font(.system(size: 16, weight: .bold))
                        .foregroundStyle(.red)
                }
                
                Text(title)
                    .font(.system(size: 15, weight: .heavy))
            }
            .foregroundStyle(foregroundColor)
            .frame(maxWidth: .infinity)
            .frame(height: 52)
            .background(
                RoundedRectangle(cornerRadius: 13, style: .continuous)
                    .fill(backgroundColor)
            )
            .overlay {
                if style == .google {
                    RoundedRectangle(cornerRadius: 13, style: .continuous)
                        .stroke(Color.gray.opacity(0.22), lineWidth: 1)
                }
            }
        }
        .buttonStyle(.plain)
    }
}

// MARK: - Divider With Text

struct DividerWithText: View {
    let text: String
    
    var body: some View {
        HStack(spacing: 14) {
            Rectangle()
                .fill(Color.gray.opacity(0.20))
                .frame(height: 1)
            
            Text(text)
                .font(.system(size: 10, weight: .heavy))
                .foregroundStyle(Color.gray)
                .lineLimit(1)
            
            Rectangle()
                .fill(Color.gray.opacity(0.20))
                .frame(height: 1)
        }
        .padding(.vertical, 2)
    }
}

// MARK: - Preview

#Preview {
    LoginView(viewModel: AuthenticationViewModel())
}
