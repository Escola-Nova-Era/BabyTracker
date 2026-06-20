import SwiftUI

struct SettingsCard: View {
    let item: SettingsItem
    var action: () -> Void
    
    var body: some View {
        Button(action: action) {
            HStack(spacing: AppSpacing.xSmall) {
                Image(systemName: item.icon)
                    .font(AppTypography.headline)
                    .foregroundColor(AppColors.surface)
                    .frame(width: 30, height: 30)
                    .background {
                        LinearGradient(
                            colors: item.tint,
                            startPoint: .topLeading,
                            endPoint: .bottomTrailing
                        )
                    }
                    .cornerRadius(8)
                VStack(alignment: .leading, spacing: 2) {
                    Text(item.title)
                        .font(AppTypography.subheadline)
                        .foregroundColor(AppColors.textPrimary)
                    
                    Text(item.detail)
                        .font(AppTypography.footnote)
                        .foregroundColor(AppColors.textSecondary)
                }
                
                Spacer()
                Image(systemName: "chevron.forward")
                    .font(AppTypography.iconSmall)
                    .foregroundColor(AppColors.textSecondary)
            }
            .padding(.vertical, 12)
            .contentShape(Rectangle())
        }
        .buttonStyle(PlainButtonStyle())
    }
}
