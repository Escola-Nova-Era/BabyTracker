import Foundation

enum NetworkConfiguration {
    static let baseURL = URL(string: "http://localhost:3000/api")!
    static let requestTimeout: TimeInterval = 30
}
