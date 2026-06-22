import SwiftUI

enum AppTypography {
    static let largeTitle = Font.system(size: 32, weight: .bold)
    static let screenTitle = Font.system(size: 24, weight: .bold)
    static let title = Font.system(size: 22, weight: .semibold)
    static let headline = Font.system(size: 18, weight: .semibold)
    static let subheadline = Font.system(size: 16, weight: .semibold)
    static let subheadlineBold = Font.system(size: 16, weight: .bold)
    static let body = Font.system(size: 14, weight: .medium)
    static let bodyStrong = Font.system(size: 14, weight: .semibold)
    static let footnote = Font.system(size: 13, weight: .medium)
    static let footnoteStrong = Font.system(size: 13, weight: .semibold)
    static let caption = Font.system(size: 12, weight: .medium)
    static let captionStrong = Font.system(size: 12, weight: .bold)
    static let overline = Font.system(size: 11, weight: .bold)
    static let metricValue = Font.system(size: 20, weight: .bold)
    static let iconSmall = Font.system(size: 14, weight: .semibold)
    static let iconMedium = Font.system(size: 17, weight: .semibold)
    static let iconTitle = Font.system(size: 20, weight: .bold)
    static let iconLarge = Font.system(size: 28, weight: .bold)
    static let iconHero = Font.system(size: 30, weight: .bold)
    static let iconExtraLarge = Font.system(size: 40, weight: .bold)
}

enum AppTheme {
    static let cornerRadius: CGFloat = 20
    static let spacing = AppSpacing.medium
}
