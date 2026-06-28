import Foundation

struct APIEndpoint {
    let path: String
    let method: HTTPMethod
    let headers: [String: String]
    let queryItems: [URLQueryItem]

    init(
        path: String,
        method: HTTPMethod = .get,
        headers: [String: String] = [:],
        queryItems: [URLQueryItem] = []
    ) {
        self.path = path
        self.method = method
        self.headers = headers
        self.queryItems = queryItems
    }

    func makeURLRequest(baseURL: URL) throws -> URLRequest {
        let url = baseURL.appendingPathComponent(path)
        var components = URLComponents(url: url, resolvingAgainstBaseURL: false)

        if !queryItems.isEmpty {
            components?.queryItems = queryItems
        }

        guard let finalURL = components?.url else {
            throw NetworkError.invalidURL
        }

        var request = URLRequest(url: finalURL)
        request.httpMethod = method.rawValue
        request.timeoutInterval = NetworkConfiguration.requestTimeout
        request.setValue("application/json", forHTTPHeaderField: "Accept")

        headers.forEach { key, value in
            request.setValue(value, forHTTPHeaderField: key)
        }

        return request
    }
}
