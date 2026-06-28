import Foundation

struct AuthUser: Codable, Equatable {
    let id: String
    let name: String
    let email: String
}

struct RegisterRequest: Encodable {
    let name: String
    let email: String
    let password: String
}

struct LoginRequest: Encodable {
    let email: String
    let password: String
}

struct LoginResponse: Decodable, Equatable {
    let token: String
    let user: AuthUser
}
