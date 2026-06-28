import Alamofire
import Foundation

protocol NetworkClientProtocol {
    func request<Response: Decodable>(_ endpoint: APIEndpoint) async throws -> Response
    func request<Request: Encodable, Response: Decodable>(_ endpoint: APIEndpoint, body: Request) async throws -> Response
}

final class NetworkClient: NetworkClientProtocol {
    private let baseURL: URL
    private let session: Session
    private let decoder: JSONDecoder
    private let encoder: JSONEncoder

    init(
        baseURL: URL = NetworkConfiguration.baseURL,
        session: Session = .default,
        decoder: JSONDecoder = JSONDecoder(),
        encoder: JSONEncoder = JSONEncoder()
    ) {
        self.baseURL = baseURL
        self.session = session
        self.decoder = decoder
        self.encoder = encoder
    }

    func request<Response: Decodable>(_ endpoint: APIEndpoint) async throws -> Response {
        let request = try endpoint.makeURLRequest(baseURL: baseURL)
        return try await perform(request)
    }

    func request<Request: Encodable, Response: Decodable>(_ endpoint: APIEndpoint, body: Request) async throws -> Response {
        var request = try endpoint.makeURLRequest(baseURL: baseURL)
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = try encoder.encode(body)

        return try await perform(request)
    }

    private func perform<Response: Decodable>(_ request: URLRequest) async throws -> Response {
        let dataResponse = await session.request(request)
            .validate(statusCode: 200...299)
            .serializingData()
            .response

        if let statusCode = dataResponse.response?.statusCode,
           !(200...299).contains(statusCode) {
            let errorResponse = dataResponse.data.flatMap { try? decoder.decode(APIErrorResponse.self, from: $0) }
            throw NetworkError.requestFailed(statusCode: statusCode, message: errorResponse?.message)
        }

        guard let data = dataResponse.data, !data.isEmpty else {
            throw NetworkError.noData
        }

        if let error = dataResponse.error {
            throw mapAlamofireError(error)
        }

        do {
            return try decoder.decode(Response.self, from: data)
        } catch {
            throw NetworkError.decodingFailed
        }
    }

    private func mapAlamofireError(_ error: AFError) -> NetworkError {
        if let statusCode = error.responseCode {
            return .requestFailed(statusCode: statusCode, message: error.errorDescription)
        }

        return .invalidResponse
    }
}

private struct APIErrorResponse: Decodable {
    let message: String?
}
