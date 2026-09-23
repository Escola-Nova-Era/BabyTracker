//
//  OnboardingFormView.swift
//  BabyTracker
//
//  Created by Ismael Costa on 15/09/26.
//

import SwiftUI

import SwiftUI

struct OnboardingFormView: View {
    
    @State private var babyName = ""
    @State private var birthDate = Date()
    @State private var gender = ""
    
    var body: some View {
        ZStack {
            
            // MARK: - Background
            
            LinearGradient(
                colors: [
                    Color.pink.opacity(0.10),
                    Color.purple.opacity(0.08),
                    Color.blue.opacity(0.08)
                ],
                startPoint: .topLeading,
                endPoint: .bottomTrailing
            )
            .ignoresSafeArea()
            
            ScrollView {
                VStack(spacing: 0) {
                    
                    // MARK: - Header
                    
                    VStack(spacing: 12) {
                        Image(systemName: "figure.and.child.holdinghands")
                            .font(.system(size: 38))
                            .foregroundStyle(.purple)
                        
                        Text("Tell us about your baby")
                            .font(.system(size: 28, weight: .bold))
                            .foregroundStyle(
                                Color(red: 0.08, green: 0.14, blue: 0.25)
                            )
                            .multilineTextAlignment(.center)
                        
                        Text("Let's personalize BabyCare for you")
                            .font(.system(size: 16))
                            .foregroundStyle(.secondary)
                            .multilineTextAlignment(.center)
                    }
                    .padding(.top, 50)
                    
                    // MARK: - Form
                    
                    VStack(spacing: 22) {
                        
                        // Baby name
                        VStack(alignment: .leading, spacing: 8) {
                            Text("Baby's name")
                                .font(.system(size: 16, weight: .semibold))
                            
                            HStack {
                                Image(systemName: "person")
                                    .foregroundStyle(.secondary)
                                
                                TextField(
                                    "Baby's name",
                                    text: $babyName
                                )
                            }
                            .padding(.horizontal, 16)
                            .frame(height: 58)
                            .background(.white)
                            .clipShape(
                                RoundedRectangle(cornerRadius: 16)
                            )
                            .overlay {
                                RoundedRectangle(cornerRadius: 16)
                                    .stroke(
                                        Color.gray.opacity(0.15),
                                        lineWidth: 1
                                    )
                            }
                        }
                        
                        // Birth date
                        VStack(alignment: .leading, spacing: 8) {
                            Text("Date of birth")
                                .font(.system(size: 16, weight: .semibold))
                            
                            HStack {
                                Image(systemName: "calendar")
                                    .foregroundStyle(.secondary)
                                
                                DatePicker(
                                    "",
                                    selection: $birthDate,
                                    displayedComponents: .date
                                )
                                .labelsHidden()
                                
                                Spacer()
                            }
                            .padding(.horizontal, 16)
                            .frame(height: 58)
                            .background(.white)
                            .clipShape(
                                RoundedRectangle(cornerRadius: 16)
                            )
                            .overlay {
                                RoundedRectangle(cornerRadius: 16)
                                    .stroke(
                                        Color.gray.opacity(0.15),
                                        lineWidth: 1
                                    )
                            }
                        }
                        
                        // Gender
                        VStack(alignment: .leading, spacing: 8) {
                            Text("Gender")
                                .font(.system(size: 16, weight: .semibold))
                            
                            HStack {
                                Image(systemName: "person.2")
                                    .foregroundStyle(.secondary)
                                
                                Picker(
                                    "Select gender",
                                    selection: $gender
                                ) {
                                    Text("Select gender")
                                        .tag("")
                                    
                                    Text("Girl")
                                        .tag("girl")
                                    
                                    Text("Boy")
                                        .tag("boy")
                                    
                                    Text("Prefer not to say")
                                        .tag("not_specified")
                                }
                                
                                Spacer()
                            }
                            .padding(.horizontal, 16)
                            .frame(height: 58)
                            .background(.white)
                            .clipShape(
                                RoundedRectangle(cornerRadius: 16)
                            )
                            .overlay {
                                RoundedRectangle(cornerRadius: 16)
                                    .stroke(
                                        Color.gray.opacity(0.15),
                                        lineWidth: 1
                                    )
                            }
                        }
                    }
                    .padding(.horizontal, 24)
                    .padding(.top, 40)
                    
                    Spacer()
                    
                    // MARK: - Continue
                    
                    Button {
                        // Próximo passo
                    } label: {
                        Text("Continue")
                            .font(.system(size: 17, weight: .bold))
                            .foregroundStyle(.white)
                            .frame(maxWidth: .infinity)
                            .frame(height: 58)
                            .background(
                                LinearGradient(
                                    colors: [
                                        .purple,
                                        .blue
                                    ],
                                    startPoint: .leading,
                                    endPoint: .trailing
                                )
                            )
                            .clipShape(
                                RoundedRectangle(cornerRadius: 18)
                            )
                    }
                    .padding(.horizontal, 24)
                    .padding(.top, 35)
                    
                    // MARK: - Page indicator
                    
                    HStack(spacing: 10) {
                        ForEach(0..<4, id: \.self) { index in
                            Circle()
                                .fill(
                                    index == 3
                                        ? Color.purple
                                        : Color.gray.opacity(0.25)
                                )
                                .frame(width: 10, height: 10)
                        }
                    }
                    .padding(.top, 28)
                    .padding(.bottom, 30)
                }
            }
        }
    }
}

#Preview {
    OnboardingFormView()
}
