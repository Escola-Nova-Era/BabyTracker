import SwiftUI

struct InsightsMetricCard: View {
    let item: InsightsCardData
    
    var body: some View {
        VStack(alignment: .leading, spacing: AppSpacing.small) {
            HStack(spacing: 8) {
                Image(systemName: item.iconName)
                    .foregroundStyle(item.iconColor)
                Text(item.title)
                    .font(.system(size: 16, weight: .semibold))
                    .foregroundStyle(AppColors.textPrimary)
            }
            
            Text(item.subtitle)
                .font(.system(size: 12, weight: .medium))
                .foregroundStyle(AppColors.textSecondary)
            
            InsightsBarChartView(data: item.values, labels: item.days, scale: item.yValues, barColor: item.barColor, chartHeight: 180
            )
            
            Text(item.summary)
                .font(.system(size: 12, weight: .medium))
                .foregroundStyle(AppColors.textSecondary)
        }
        .padding(AppSpacing.large)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(AppColors.surface)
        .cornerRadius(20)
    }
}

