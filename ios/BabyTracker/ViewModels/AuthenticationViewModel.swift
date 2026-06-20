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
    
    // Estado de autenticação
    @Published private(set) var authenticatedUser: AuthUser?
    @Published private(set) var authToken: String?
    @Published private(set) var isLoading = false
    @Published var errorMessage: String?

    private let authService: AuthServiceProtocol

    init(authService: AuthServiceProtocol = AuthService()) {
        self.authService = authService
    }
    
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
        guard canCreateAccount else { return }

        Task { @MainActor in
            await performRequest {
                let user = try await self.authService.register(
                    name: self.createName,
                    email: self.createEmail,
                    password: self.createPassword
                )
                self.authenticatedUser = user
            }
        }
    }
    
    func signIn() {
        guard canSignIn else { return }

        Task { @MainActor in
            await performRequest {
                let response = try await self.authService.login(
                    email: self.signInEmail,
                    password: self.signInPassword
                )
                self.authenticatedUser = response.user
                self.authToken = response.token
            }
        }
    }
    
    func continueWithGoogle() {
        errorMessage = "Google sign-in is not available yet."
    }
    
    func continueWithApple() {
        errorMessage = "Apple sign-in is not available yet."
    }

    @MainActor
    private func performRequest(_ request: @escaping () async throws -> Void) async {
        isLoading = true
        errorMessage = nil

        do {
            try await request()
        } catch {
            errorMessage = error.localizedDescription
        }

        isLoading = false
    }
}
