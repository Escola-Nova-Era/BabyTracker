import SwiftUI

struct InsightsView: View {
    @StateObject private var viewModel = InsightsViewModel()
    
    var body: some View {
        NavigationStack {
            ZStack {
                LinearGradient(colors: [AppColors.backgroundTop, AppColors.backgroundBottom], startPoint: .topLeading, endPoint: .bottomTrailing)
                    .ignoresSafeArea()
                ScrollView {
                    VStack(alignment: .leading, spacing: AppSpacing.xSmall) {
                        Text("Insights")
                            .font(AppTypography.screenTitle)
                            .foregroundStyle(AppColors.textPrimary)
                        
                        Text("Track Emma's daily activities")
                            .font(AppTypography.body)
                            .foregroundStyle(AppColors.textSecondary)
                        
                        Spacer()
                        
                        VStack(alignment: .leading, spacing: AppSpacing.large) {
                            InsightsHeaderCard()
                            
                            ForEach(viewModel.cards) { item in
                                InsightsMetricCard(item: item)
                            }
                        }
                    }
                    .padding(AppSpacing.large)
                }
            }
        }
    }
}
