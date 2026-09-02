import SwiftUI


struct ActionButton: Identifiable {
    let id = UUID()
    let title: String
    let icon: String
    let colors: Color
    /// Tipo de evento que o botão registra (usado para abrir o Quick Action dialog).
    let kind: TrackingKind
}
