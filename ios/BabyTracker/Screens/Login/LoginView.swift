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
                    AppColors.backgroundTop,
                    AppColors.backgroundBottom
                ],
                startPoint: .topLeading,
                endPoint: .bottomTrailing
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
                                style: .google
                            ) {
                                viewModel.continueWithGoogle()
                            }
                            
                            SocialLoginButton(
                                title: "Continue with Apple",
                                style: .apple
                            ) {
                                viewModel.continueWithApple()
                            }
                        }
                        .padding(.horizontal, 22)
                        .padding(.top, 28)
                        .padding(.bottom, 24)
                        .background(
                            RoundedRectangle(cornerRadius: AppTheme.cornerRadius, style: .continuous)
                                .fill(AppColors.surface)
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

// MARK: - Preview

#Preview {
    LoginView(viewModel: AuthenticationViewModel())
}
