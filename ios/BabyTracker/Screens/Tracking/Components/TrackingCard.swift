import SwiftUI

struct TrackingCard: View {
    let item: TrackingItem
    let isLast: Bool
    
    var body: some View {
        HStack(alignment: .top, spacing: AppSpacing.small) {
            ZStack {
                Circle()
                    .fill(item.tint)
                    .frame(width: 32, height: 32).opacity(0.5)
                
                Image(systemName: item.icon)
                    .font(AppTypography.subheadline)
                    .foregroundColor(item.tint)
                
            }
            .frame(width: 22)
            
            VStack(alignment: .leading, spacing: AppSpacing.xxSmall) {
                HStack {
                    Text(item.title)
                        .font(AppTypography.bodyStrong)
                        .foregroundStyle(AppColors.textPrimary)
                    
                    Spacer()
                    
                    Text(item.time)
                        .font(AppTypography.captionStrong)
                        .foregroundStyle(AppColors.textSecondary)
                }
                HStack(spacing: AppSpacing.xSmall) {
                    Image(systemName: "water.waves")
                        .font(AppTypography.caption)
                        .foregroundStyle(item.tint)
                    
                    Text(item.detail)
                        .font(AppTypography.caption)
                        .foregroundStyle(AppColors.textSecondary)
                }
            }
            .padding(.horizontal, AppSpacing.small)
            .padding(.vertical, AppSpacing.xLarge)
            .background(AppColors.surfaceMuted)
            .cornerRadius(12)
            .overlay(
                RoundedRectangle(cornerRadius: 12)
                    .stroke(AppColors.divider.opacity(0.7), lineWidth: 1)
            )
        }
        // Se for último, padding 0, se não, small.
        .padding(.bottom, isLast ? 0 : AppSpacing.small)
    }
}
