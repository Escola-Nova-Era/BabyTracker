import SwiftUI

struct SummaryCard: View {
    
    let item: SummaryItem
    
    
    var body: some View {
        VStack(alignment: .leading, spacing: AppSpacing.xSmall) {
            Image(systemName: item.icon)
                .font(.system(size: 14, weight: .semibold))
                .foregroundColor(item.tintIcon)
                .frame(width: 28, height: 28)
                .background(item.tint.first ?? AppColors.surface)
                .clipShape(Circle())
                .shadow(color: .black.opacity(0.2), radius: 10, x: 0, y: 6)
            
            Text(item.title)
                .font(.system(size: 12, weight: .bold))
                .foregroundStyle(AppColors.textSecondary)
            
            Text(item.value)
                .font(Font.system(size: 20, weight: .bold))
                .foregroundStyle(AppColors.textPrimary)
            
            Text(item.detail)
                .font(.system(size: 12, weight: .bold))
                .foregroundStyle(AppColors.textTertiary)
        }
        .padding(AppSpacing.medium)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(
            LinearGradient(colors: item.tint, startPoint: .topLeading, endPoint: .bottomTrailing)
        )
        .cornerRadius(16)
    }
}

#Preview {
    SummaryCard(item: SummaryItem(
        title: "Last feed",
        value: "2h ago",
        detail: "120 ml",
        icon: "arrow.up",
        tint: [AppColors.yellowSoft, AppColors.yellowMuted],
        tintIcon: AppColors.orangeSoft
    ))
}
