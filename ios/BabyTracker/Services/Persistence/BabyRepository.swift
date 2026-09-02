import Foundation
import SwiftData

protocol BabyRepositoryProtocol {
    func currentBaby() throws -> Baby?

    // Cria ou atualiza o bebê atual e retorna a instância salva.
    func saveBaby(
        name: String,
        birthDate: Date?,
        weightKg: Double?,
        heightCm: Double?,
        photoName: String?
    ) throws -> Baby
}

struct BabyRepository: BabyRepositoryProtocol {
    private let context: ModelContext

    init(context: ModelContext) {
        self.context = context
    }

    func currentBaby() throws -> Baby? {
        var descriptor = FetchDescriptor<Baby>()
        descriptor.fetchLimit = 1
        return try context.fetch(descriptor).first
    }

    @discardableResult
    func saveBaby(
        name: String,
        birthDate: Date?,
        weightKg: Double?,
        heightCm: Double?,
        photoName: String?
    ) throws -> Baby {
        if let baby = try currentBaby() {
            baby.name = name
            baby.birthDate = birthDate
            baby.weightKg = weightKg
            baby.heightCm = heightCm
            baby.photoName = photoName
            try context.save()
            return baby
        } else {
            let baby = Baby(
                name: name,
                birthDate: birthDate,
                weightKg: weightKg,
                heightCm: heightCm,
                photoName: photoName
            )
            context.insert(baby)
            try context.save()
            return baby
        }
    }
}
