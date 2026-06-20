import SwiftUI

struct CreateAccountView: View {
    @ObservedObject var viewModel: AuthenticationViewModel
    let onSignInTap: () -> Void
    
    var body: some View {
        ZStack {
            LinearGradient(
                colors: [AppColors.backgroundTop, AppColors.backgroundBottom],
                startPoint: .topLeading,
                endPoint: .bottomTrailing
            )
            .ignoresSafeArea()
            
            ScrollView(showsIndicators: false) {
                VStack(spacing: AppSpacing.xLarge) {
                    ZStack {
                        RoundedRectangle(cornerRadius: 24, style: .continuous)
                            .fill(
                                LinearGradient(
                                    colors: [AppColors.accent.opacity(0.8), AppColors.primarySoft],
                                    startPoint: .topLeading,
                                    endPoint: .bottomTrailing
                                )
                            )
                            .frame(width: 96, height: 96)
                        
                        Image(systemName: "heart.fill")
                            .font(AppTypography.iconLarge)
                            .foregroundStyle(.white)
                    }
                    .padding(.top, AppSpacing.xxxLarge)
                    
                    VStack(spacing: AppSpacing.xSmall) {
                        Text("Create Account")
                            .font(AppTypography.largeTitle)
                            .foregroundStyle(AppColors.textPrimary)
                        
                        Text("Join us on this beautiful journey")
                            .font(AppTypography.body)
                            .multilineTextAlignment(.center)
                            .foregroundStyle(AppColors.textSecondary)
                    }
                    .padding(.horizontal, AppSpacing.medium)
                    
                    VStack(alignment: .leading, spacing: AppSpacing.large) {
                        VStack(alignment: .leading, spacing: AppSpacing.xSmall) {
                            Text("Parent Name")
                                .font(AppTypography.footnoteStrong)
                                .foregroundStyle(AppColors.textSecondary)
                            
                            TextField("Enter your name", text: $viewModel.createName)
                                .padding(.horizontal, AppSpacing.medium)
                                .frame(height: 52)
                                .background(AppColors.surfaceMuted)
                                .cornerRadius(16)
                                .overlay(
                                    RoundedRectangle(cornerRadius: 16, style: .continuous)
                                        .stroke(AppColors.border, lineWidth: 1)
                                )
                        }
                        
                        VStack(alignment: .leading, spacing: AppSpacing.xSmall) {
                            Text("Email")
                                .font(AppTypography.footnoteStrong)
                                .foregroundStyle(AppColors.textSecondary)
                            
                            TextField("your@email.com", text: $viewModel.createEmail)
                                .keyboardType(.emailAddress)
                                .textInputAutocapitalization(.never)
                                .autocorrectionDisabled(true)
                                .padding(.horizontal, AppSpacing.medium)
                                .frame(height: 52)
                                .background(AppColors.surfaceMuted)
                                .cornerRadius(16)
                                .overlay(
                                    RoundedRectangle(cornerRadius: 16, style: .continuous)
                                        .stroke(AppColors.border, lineWidth: 1)
                                )
                        }
                        
                        VStack(alignment: .leading, spacing: AppSpacing.xSmall) {
                            Text("Password")
                                .font(AppTypography.footnoteStrong)
                                .foregroundStyle(AppColors.textSecondary)
                            
                            SecureField("Create password", text: $viewModel.createPassword)
                                .textInputAutocapitalization(.never)
                                .autocorrectionDisabled(true)
                                .padding(.horizontal, AppSpacing.medium)
                                .frame(height: 52)
                                .background(AppColors.surfaceMuted)
                                .cornerRadius(16)
                                .overlay(
                                    RoundedRectangle(cornerRadius: 16, style: .continuous)
                                        .stroke(AppColors.border, lineWidth: 1)
                                )
                        }
                        
                        Button {
                            viewModel.acceptedTerms.toggle()
                        } label: {
                            HStack(alignment: .top, spacing: AppSpacing.small) {
                                Image(systemName: viewModel.acceptedTerms ? "checkmark.square.fill" : "square")
                                    .font(AppTypography.headline)
                                    .foregroundStyle(viewModel.acceptedTerms ? AppColors.success : AppColors.textTertiary)
                                
                                Text("I agree to the Terms & Privacy Policy")
                                    .font(AppTypography.footnote)
                                    .foregroundStyle(AppColors.textSecondary)
                                    .multilineTextAlignment(.leading)
                            }
                        }
                        .buttonStyle(.plain)
                        
                        Button {
                            viewModel.createAccount()
                        } label: {
                            Text("Create Account")
                                .font(AppTypography.subheadline)
                                .foregroundStyle(.white)
                                .frame(maxWidth: .infinity)
                                .frame(height: 54)
                                .background(
                                    LinearGradient(
                                        colors: [AppColors.accent, AppColors.primarySoft],
                                        startPoint: .leading,
                                        endPoint: .trailing
                                    )
                                )
                                .cornerRadius(18)
                                .opacity(viewModel.canCreateAccount ? 1.0 : 0.6)
                        }
                        .disabled(!viewModel.canCreateAccount)
                        
                        VStack(spacing: AppSpacing.medium) {
                            HStack(spacing: AppSpacing.small) {
                                Rectangle()
                                    .fill(AppColors.divider)
                                    .frame(height: 1)
                                
                                Text("OR SIGN IN WITH")
                                    .font(AppTypography.overline)
                                    .foregroundStyle(AppColors.textTertiary)
                                Rectangle()
                                    .fill(AppColors.divider)
                                    .frame(height: 1)
                            }
                            
                            VStack(spacing: AppSpacing.small) {
                                Button {
                                    viewModel.continueWithGoogle()
                                }
                                label: {
                                    HStack(spacing: AppSpacing.small) {
                                        Image("google-logo")
                                            .resizable()
                                            .frame(width: 28, height: 28)

                                        Text("Continue with Google")
                                            .font(AppTypography.subheadline)
                                            .foregroundStyle(AppColors.textPrimary)
                                    }
                                    .padding(.horizontal, AppSpacing.medium)
                                    .frame(maxWidth: .infinity)
                                    .frame(height: 54)
                                    .background(AppColors.surfaceStrong)
                                    .cornerRadius(18)
                                    .overlay(
                                        RoundedRectangle(cornerRadius: 18, style: .continuous).stroke(AppColors.divider, lineWidth: 1)
                                    )
                                    .shadow(color: Color.black.opacity(0.04), radius: 10, y: 6)
                                }
                                .buttonStyle(.plain)
                                
                                Button {
                                    viewModel.continueWithApple()
                                }
                                label: {
                                    HStack(spacing: AppSpacing.small) {
                                        Image(systemName: "applelogo")
                                            .font(AppTypography.subheadline)
                                            .foregroundStyle(.white)
                                            .frame(width: 28, height: 28)

                                        Text("Continue with Apple")
                                            .font(AppTypography.subheadline)
                                            .foregroundStyle(.white)
                                    }
                                    .padding(.horizontal, AppSpacing.medium)
                                    .frame(maxWidth: .infinity)
                                    .frame(height: 54)
                                    .background(AppColors.textPrimary)
                                    .cornerRadius(18)
                                    .overlay(
                                        RoundedRectangle(cornerRadius: 18, style: .continuous).stroke(AppColors.divider, lineWidth: 1)
                                    )
                                    .shadow(color: Color.black.opacity(0.14), radius: 12, y: 8)
                                }
                                .buttonStyle(.plain)
                            }
                        }
                        
                        HStack(spacing: AppSpacing.xxSmall) {
                            Text("Already have an account?")
                                .font(AppTypography.body)
                                .foregroundStyle(AppColors.textSecondary)
                            
                            Button("Sign in", action: onSignInTap)
                                .font(AppTypography.bodyStrong)
                                .foregroundStyle(AppColors.primary)
                        }
                        .frame(maxWidth: .infinity)
                        .padding(.top, AppSpacing.xSmall)
                        
                    }
                    .padding(AppSpacing.xLarge)
                    .background(AppColors.surface)
                    .cornerRadius(28)
                    .overlay(
                        RoundedRectangle(cornerRadius: 28, style: .continuous)
                            .stroke(AppColors.border, lineWidth: 1)
                    )
                    .shadow(color: Color.black.opacity(0.06), radius: 20, y: 12)
                }
            }
            .padding(.top, AppSpacing.xxLarge)
            .padding(.bottom, AppSpacing.xLarge)
        }
        .ignoresSafeArea(.all)
    }
}
