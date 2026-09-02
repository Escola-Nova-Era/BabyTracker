import SwiftUI
import SwiftData

struct InsightsView: View {
    @Environment(\.modelContext) private var modelContext
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
                        
                        Text("Track your baby's daily activities")
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
        .task {
            viewModel.load(context: modelContext)
        }
    }
}

#Preview {
    InsightsView()
        .modelContainer(for: [TrackingEvent.self, Baby.self], inMemory: true)
}
