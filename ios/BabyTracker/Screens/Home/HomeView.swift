import SwiftUI

struct HomeView: View {
    
    @StateObject private var homeViewModel = HomeViewModel()
    
    var body: some View {
        NavigationStack {
            
            ZStack {
                LinearGradient(
                    colors: [AppColors.backgroundBottom, AppColors.backgroundTop],
                    startPoint: .topLeading,
                    endPoint: .bottomTrailing
                )
                    .ignoresSafeArea()
                
                ScrollView {
                    VStack(alignment: .leading, spacing: AppSpacing.large) {
                        HStack {
                            VStack(alignment: .leading, spacing: AppSpacing.xxSmall) {
                                Text("Hello, Parents 👋")
                                .font(.system(size: 24, weight: .bold))
                                .foregroundStyle(AppColors.textPrimary)
                                
                                Text("Let's track Emma's day")
                                    .font(.system(size: 14, weight: .medium))
                                    .foregroundStyle(AppColors.textSecondary)
                            }
                            
                            Spacer()
                            
                            Image(systemName: "bell.badge")
                                .font(.system(size: 17, weight: .semibold))
                                .foregroundStyle(AppColors.textPrimary)
                                .frame(width: 40, height: 40)
                                .background(AppColors.surfaceStrong)
                                .clipShape(Circle())
                        }
                        
                        HStack(spacing: AppSpacing.small) {
                           Circle()
                                .fill(
                            LinearGradient(
                                colors: [AppColors.accent, AppColors.mint],
                                startPoint: .topLeading,
                                endPoint: .bottomTrailing
                                )
                            )
                            .frame(width: 60, height: 60)
                            .overlay(
                                Image(systemName: "person.fill.viewfinder")
                                    .font(.system(size: 30, weight: .bold))
                                    .foregroundStyle(AppColors.textPrimary)
                            )
                            
                            VStack(alignment: .leading, spacing: AppSpacing.xxSmall) {
                                Text("Emma Rose")
                                    .font(.system(size: 24, weight: .semibold))
                                    .foregroundStyle(AppColors.textPrimary)
                                
                                Text("42 days old • 3.8 kg")
                                    .font(.system(size: 14, weight: .semibold))
                                    .foregroundStyle(AppColors.textSecondary)
                            }
                            
                            Spacer()
                            Circle()
                                .fill(AppColors.purpleSoft)
                                .frame(width: 40, height: 40)
                                .overlay(
                                    Image(systemName: "pencil")
                                        .font(.system(size: 20, weight: .bold))
                                        .foregroundStyle(AppColors.textPrimary)
                                )
                        }
                        .padding(AppSpacing.medium)
                        .background(AppColors.surface)
                        .cornerRadius(20)
                        
                      Text("TODAY'S, SUMMARY")
                            .font(.system(size: 14, weight: .semibold))
                            .foregroundStyle(AppColors.textSecondary)
                        
                        LazyVGrid(columns: Array(repeating: GridItem(.flexible(), spacing: AppSpacing.small), count: 2), spacing: AppSpacing.small) {
                            
                            ForEach(homeViewModel.summaryItems) { item in
                                SummaryCard(item: item)
                            }
                        }
                        
                        Text("QUICK ACTIONS")
                            .font(.system(size: 14, weight: .semibold))
                            .foregroundStyle(AppColors.textSecondary)
                        
                        LazyVGrid(columns: Array(repeating: GridItem(.flexible(), spacing: AppSpacing.small), count: 2), spacing: AppSpacing.small) {
                            
                            ForEach(homeViewModel.actionButtons) { item in
                                QuickActionButton(item: item)
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

#Preview("Dark") {
    HomeView()
        .preferredColorScheme(.dark)
}
