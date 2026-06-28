import Foundation

enum NetworkError: LocalizedError {
    case invalidURL
    case invalidResponse
    case requestFailed(statusCode: Int, message: String?)
    case decodingFailed
    case noData

    var errorDescription: String? {
        switch self {
        case .invalidURL:
            return "Invalid API URL."
        case .invalidResponse:
            return "Invalid server response."
        case let .requestFailed(statusCode, message):
            return message ?? "Request failed with status code \(statusCode)."
        case .decodingFailed:
            return "Unable to decode server response."
        case .noData:
            return "The server returned no data."
        }
    }
}
