import SwiftUI
import SwiftData

struct ProfileView: View {
    @Environment(\.modelContext) private var modelContext
    @StateObject private var viewModel = ProfileViewModel()

    var body: some View {
        NavigationStack {
            ZStack {
                // Background
                LinearGradient(
                    colors: [AppColors.backgroundBottom, AppColors.backgroundTop],
                    startPoint: .topLeading,
                    endPoint: .bottomTrailing
                )
                .ignoresSafeArea()
                
                ScrollView() {
                    VStack(alignment: .leading, spacing: AppSpacing.large) {
                        // --- HEADER ---
                        headerSection
                        
                        // --- BABY INFO CARD ---
                        babyInfoCard
                        
                        // --- SETTINGS LIST ---
                        VStack(alignment: .leading, spacing: AppSpacing.large) {
                            Text("SETTINGS")
                                .font(AppTypography.bodyStrong)
                                .foregroundStyle(AppColors.textSecondary)
                                .padding(.top, AppSpacing.medium)
                            
                            VStack(spacing: AppSpacing.xSmall) {
                                ForEach(viewModel.settingItems) { item in
                                    SettingsCard(item: item) {
                                        viewModel.handleTap(item: item)
                                    }
                                    
                                    if item.id != viewModel.settingItems.last?.id {
                                        Divider()
                                            .background(AppColors.textTertiary.opacity(0.2))
                                    }
                                }
                            }
                            .padding(.horizontal, AppSpacing.medium)
                            .background(AppColors.surface)
                            .cornerRadius(AppTheme.cornerRadius)
                        }
                    }
                    .padding(.horizontal, AppSpacing.medium)
                }
            }
        }
        .navigationBarHidden(true)
        .task {
            viewModel.load(context: modelContext)
        }
    }

    // MARK: - Subviews para organização dos elementos da UI.
    private var headerSection: some View {
        HStack {
            VStack(alignment: .leading, spacing: AppSpacing.xxSmall) {
                Text("Profile")
                    .font(AppTypography.screenTitle)
                    .foregroundStyle(AppColors.textPrimary)
                
                Text("Your baby's information.")
                    .font(AppTypography.body)
                    .foregroundStyle(AppColors.textSecondary)
            }
            Spacer()
            Button {} label: {
                Image(systemName: "gear.badge")
                    .font(AppTypography.iconMedium)
                    .foregroundStyle(AppColors.textPrimary)
                    .frame(width: 40, height: 40)
                    .background(AppColors.surfaceStrong)
                    .clipShape(Circle())
            }
        }
    }
    
    private var babyInfoCard: some View {
        VStack(spacing: AppSpacing.small) {
            // Foto/Avatar
            Spacer()
            
            Circle()
                .fill(
                    LinearGradient(
                        colors: [AppColors.accent, AppColors.mint],
                        startPoint: .topLeading,
                        endPoint: .bottomTrailing
                    )
                )
                .frame(width: 90, height: 90)
                .overlay(
                    Image(systemName: "person.fill.viewfinder")
                        .font(AppTypography.iconExtraLarge)
                        .foregroundStyle(AppColors.surface)
                )
            
            VStack(alignment: .center, spacing: AppSpacing.xxSmall) {
                Text(viewModel.babyName)
                    .font(AppTypography.screenTitle)
                    .foregroundStyle(AppColors.surface)
                Text(viewModel.bornLabel)
                    .font(AppTypography.bodyStrong)
                    .foregroundStyle(AppColors.surface)
            }
            Spacer()

            // Stats Row
            HStack(spacing: 30) {
                statItem(value: viewModel.ageLabel, label: "Age")
                Divider()
                statItem(value: viewModel.weightLabel, label: "Weight")
                Divider()
                statItem(value: viewModel.heightLabel, label: "Height")
            }
            Spacer()
        }
        .frame(maxWidth: .infinity)
        .padding(AppSpacing.medium)
        .background(
            LinearGradient(
                colors: [AppColors.lavander, AppColors.accent],
                startPoint: .topLeading,
                endPoint: .bottomTrailing
            )
        )
        .cornerRadius(AppTheme.cornerRadius)
    }
    
    private func statItem(value: String, label: String) -> some View {
        VStack(spacing: AppSpacing.xxSmall) {
            Text(value)
                .font(AppTypography.headline)
                .foregroundStyle(AppColors.surface)
            Text(label)
                .font(AppTypography.bodyStrong)
                .foregroundStyle(AppColors.surface)
        }
    }
}
