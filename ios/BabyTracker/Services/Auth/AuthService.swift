import Foundation

protocol AuthServiceProtocol {
    func register(name: String, email: String, password: String) async throws -> AuthUser
    func login(email: String, password: String) async throws -> LoginResponse
}

final class AuthService: AuthServiceProtocol {
    private let networkClient: NetworkClientProtocol

    init(networkClient: NetworkClientProtocol = NetworkClient()) {
        self.networkClient = networkClient
    }

    func register(name: String, email: String, password: String) async throws -> AuthUser {
        let endpoint = APIEndpoint(path: "auth/register", method: .post)
        let request = RegisterRequest(name: name, email: email, password: password)
        return try await networkClient.request(endpoint, body: request)
    }

    func login(email: String, password: String) async throws -> LoginResponse {
        let endpoint = APIEndpoint(path: "auth/login", method: .post)
        let request = LoginRequest(email: email, password: password)
        return try await networkClient.request(endpoint, body: request)
    }
}

final class MockAuthService: AuthServiceProtocol {
    func register(name: String, email: String, password: String) async throws -> AuthUser {
        AuthUser(id: UUID().uuidString, name: name, email: email)
    }

    func login(email: String, password: String) async throws -> LoginResponse {
        LoginResponse(
            token: "mock-auth-token",
            user: AuthUser(id: UUID().uuidString, name: "Mock User", email: email)
        )
    }
}
