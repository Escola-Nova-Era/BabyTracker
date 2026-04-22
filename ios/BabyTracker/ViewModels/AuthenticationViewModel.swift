//
//  AuthenticationViewModel.swift
//  BabyTracker

import Foundation
import Combine

// ViewModel central da autenticação
final class AuthenticationViewModel: ObservableObject {
    
    // Campos de criação de conta
    @Published var createName = ""
    @Published var createEmail = ""
    @Published var createPassword = ""
    @Published var acceptedTerms = false
    
    // Campos de login
    @Published var signInEmail = ""
    @Published var signInPassword = ""
    
    // Regra para permitir criação da conta
    var canCreateAccount: Bool {
        !createName.isEmpty && !createEmail.isEmpty && !createPassword.isEmpty && acceptedTerms
    }
    
    // Regra para permitir login
    var canSignIn: Bool {
        !signInEmail.isEmpty && !signInPassword.isEmpty
    }
    
    // MARK: Integração com backend/autenticação real
    func createAccount() {
    
    }
    
    func signIn() {
       
    }
    
    func continueWithGoogle() {
        
    }
    
    func continueWithApple() {
        
    }
}
