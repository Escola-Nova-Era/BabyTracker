import Foundation

/// Helpers de formatação de data usados nas telas (Home, Tracking, Insights).
/// Centralizados aqui para manter a formatação consistente entre os ViewModels.
enum DateFormatting {

    /// Horário no formato de relógio local (ex.: "2:30 PM").
    static func clockTime(_ date: Date) -> String {
        date.formatted(date: .omitted, time: .shortened)
    }

    /// Rótulo curto do dia da semana (ex.: "Mon"), usado nos gráficos de Insights.
    static func shortWeekday(_ date: Date) -> String {
        date.formatted(.dateTime.weekday(.abbreviated))
    }

    /// Diferença relativa compacta (ex.: "just now", "45m ago", "2h ago", "Yesterday", "3d ago").
    static func relativeShort(from date: Date, to reference: Date = .now) -> String {
        let seconds = max(0, reference.timeIntervalSince(date))
        let minutes = Int(seconds / 60)
        let hours = Int(seconds / 3600)
        let days = Int(seconds / 86_400)

        if minutes < 1 { return "just now" }
        if minutes < 60 { return "\(minutes)m ago" }
        if hours < 24 { return "\(hours)h ago" }
        if days == 1 { return "Yesterday" }
        return "\(days)d ago"
    }
}
