import Foundation
import SwiftData

/// Perfil do bebê. No MVP trabalhamos com um único bebê por app,
/// criado no onboarding e editado na tela de Profile.
@Model
final class Baby {
    var id: UUID
    var name: String
    var birthDate: Date?
    var weightKg: Double?
    var heightCm: Double?
    var photoName: String?

    init(
        id: UUID = UUID(),
        name: String,
        birthDate: Date? = nil,
        weightKg: Double? = nil,
        heightCm: Double? = nil,
        photoName: String? = nil
    ) {
        self.id = id
        self.name = name
        self.birthDate = birthDate
        self.weightKg = weightKg
        self.heightCm = heightCm
        self.photoName = photoName
    }

    /// Idade em dias (para o header da Home: "42 days old").
    var ageInDays: Int? {
        guard let birthDate else { return nil }
        return Calendar.current.dateComponents([.day], from: birthDate, to: .now).day
    }

    /// Idade em semanas completas (usada na tela de Profile).
    var ageInWeeks: Int? {
        guard let ageInDays else { return nil }
        return ageInDays / 7
    }
}
