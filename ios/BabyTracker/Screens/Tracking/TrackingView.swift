import SwiftUI

struct TrackingView: View {
    @StateObject var viewModel = TrackingViewModel()
    
    var body: some View {
        NavigationStack {
            ZStack {
                LinearGradient(colors: [AppColors.backgroundTop, AppColors.backgroundBottom], startPoint: .topLeading, endPoint: .bottomTrailing)
                    .ignoresSafeArea()
                ScrollView {
                    VStack(alignment: .leading, spacing: AppSpacing.medium) {
                        HStack(alignment: .top) {
                            VStack(alignment: .leading, spacing: AppSpacing.xxSmall) {
                                Text("Today's Timeline")
                                    .font(AppTypography.screenTitle)
                                    .foregroundStyle(AppColors.textPrimary)
                                
                                Text("January 26, 2026")
                                    .font(AppTypography.body)
                                    .foregroundStyle(AppColors.textSecondary)
                            }
                            
                            Spacer()
                            
                            Button {
                            } label: {
                                Image(systemName: "line.3.horizontal.decrease")
                                    .font(AppTypography.iconSmall)
                                    .foregroundStyle(AppColors.textSecondary)
                                    .frame(width: 30, height: 30)
                                    .background(AppColors.surfaceMuted)
                            }
                            .buttonStyle(.plain)
                        }
                        
                        ZStack(alignment: .topLeading) {
                            Rectangle()
                                .fill(AppColors.highlight.opacity(0.6))
                                .frame(width: 2)
                                .padding(.leading, 10)
                                .padding(.top, 22)
                                .padding(.bottom, 12)
                            
                            VStack(spacing: 0) {
                                ForEach(viewModel.trackingItems) { item in
                                    TrackingCard(item: item, isLast: false)
                                }
                            }
                        }
                    }
                    .padding(AppSpacing.large)
                }
            }
        }
        .navigationBarHidden(true)
    }
}
