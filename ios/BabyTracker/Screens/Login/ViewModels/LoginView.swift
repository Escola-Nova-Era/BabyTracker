//
//  LoginView.swift
//  BabyTracker
//
//  Created by Gabriel Silva Custodio on 17/06/2026.
//


import SwiftUI

// MARK: - Create Account View

struct LoginView: View {
    @State private var parentName: String = ""
    @State private var email: String = ""
    @State private var password: String = ""
    @State private var agreedToTerms: Bool = false
    
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
                                title: "Parent Name",
                                placeholder: "Enter your name",
                                systemImage: "person",
                                text: $parentName,
                                keyboardType: .default
                            )
                            
                            AuthTextField(
                                title: "Email",
                                placeholder: "your@email.com",
                                systemImage: "envelope",
                                text: $email,
                                keyboardType: .emailAddress
                            )
                            
                            PasswordTextField(
                                title: "Password",
                                placeholder: "Create password",
                                text: $password
                            )
                            
                            TermsCheckbox(isChecked: $agreedToTerms)
                            
                            PrimaryGradientButton(title: "Create Account") {
                                createAccount()
                            }
                            
                            DividerWithText(text: "OR SIGN UP WITH")
                            
                            SocialLoginButton(
                                title: "Continue with Google",
                                iconText: "G",
                                style: .google
                            ) {
                                continueWithGoogle()
                            }
                            
                            SocialLoginButton(
                                title: "Continue with Apple",
                                systemImage: "apple.logo",
                                style: .apple
                            ) {
                                continueWithApple()
                            }
                            
                            SignInFooter()
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
    
    private func createAccount() {
        print("Create account tapped")
    }
    
    private func continueWithGoogle() {
        print("Google login tapped")
    }
    
    private func continueWithApple() {
        print("Apple login tapped")
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
                Text("Create Account")
                    .font(.system(size: 24, weight: .heavy, design: .rounded))
                    .foregroundStyle(Color.black.opacity(0.88))
                
                Text("Join us on this beautiful journey")
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
            
            Text("At least 8 characters")
                .font(.system(size: 11, weight: .medium))
                .foregroundStyle(Color.gray)
        }
    }
}

// MARK: - Terms Checkbox

struct TermsCheckbox: View {
    @Binding var isChecked: Bool
    
    var body: some View {
        HStack(alignment: .top, spacing: 10) {
            Button {
                isChecked.toggle()
            } label: {
                ZStack {
                    RoundedRectangle(cornerRadius: 3, style: .continuous)
                        .fill(isChecked ? Color.purple.opacity(0.85) : Color.clear)
                        .frame(width: 16, height: 16)
                    
                    RoundedRectangle(cornerRadius: 3, style: .continuous)
                        .stroke(
                            isChecked ? Color.purple.opacity(0.85) : Color.gray.opacity(0.45),
                            lineWidth: 1
                        )
                        .frame(width: 16, height: 16)
                    
                    if isChecked {
                        Image(systemName: "checkmark")
                            .font(.system(size: 10, weight: .bold))
                            .foregroundStyle(.white)
                    }
                }
            }
            .buttonStyle(.plain)
            
            Text(makeTermsText())
                .font(.system(size: 11, weight: .medium))
                .foregroundStyle(Color.gray)
                .fixedSize(horizontal: false, vertical: true)
            
            Spacer(minLength: 0)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }
    
    private func makeTermsText() -> AttributedString {
        var text = AttributedString("I agree to the Terms of Service and Privacy Policy")
        
        if let termsRange = text.range(of: "Terms of Service") {
            text[termsRange].foregroundColor = .purple
            text[termsRange].font = .system(size: 11, weight: .semibold)
        }
        
        if let privacyRange = text.range(of: "Privacy Policy") {
            text[privacyRange].foregroundColor = .purple
            text[privacyRange].font = .system(size: 11, weight: .semibold)
        }
        
        return text
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

// MARK: - Sign In Footer

struct SignInFooter: View {
    var body: some View {
        HStack(spacing: 4) {
            Text("Already have an account?")
                .foregroundStyle(Color.gray)
            
            Button {
                print("Sign in tapped")
            } label: {
                Text("Sign In")
                    .fontWeight(.heavy)
                    .foregroundStyle(Color.purple.opacity(0.85))
            }
            .buttonStyle(.plain)
        }
        .font(.system(size: 12, weight: .medium))
        .padding(.top, 4)
    }
}

// MARK: - Preview

#Preview {
    LoginView()
}
