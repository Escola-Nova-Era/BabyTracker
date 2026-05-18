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
                    .font(.system(size: 16))
                    .foregroundColor(item.tint)
                
            }
            .frame(width: 22)
            
            VStack(alignment: .leading, spacing: AppSpacing.xxSmall) {
                HStack {
                    Text(item.title)
                        .font(.system(size: 14, weight: .bold))
                        .foregroundStyle(AppColors.textPrimary)
                    
                    Spacer()
                    
                    Text(item.time)
                        .font(.system(size: 12, weight: .bold))
                        .foregroundStyle(AppColors.textSecondary)
                }
                HStack(spacing: AppSpacing.xSmall) {
                    Image(systemName: "water.waves")
                        .font(.system(size: 12, weight: .medium))
                        .foregroundStyle(item.tint)
                    
                    Text(item.detail)
                        .font(.system(size: 12, weight: .medium))
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
