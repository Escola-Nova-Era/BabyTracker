import SwiftUI

struct ProfileView: View {
   
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
                                .font(.system(size: 14, weight: .semibold))
                                .foregroundStyle(AppColors.textSecondary)
                                .padding(.top, AppSpacing.medium)
                            
                            VStack(spacing: AppSpacing.xSmall){
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
                            .cornerRadius(20)
                        }
                    }
                    .padding(.horizontal, AppSpacing.medium)
                }
            }
        }
        .navigationBarHidden(true)
    }
    
    // MARK: - Subviews para organização dos elementos da UI.
    private var headerSection: some View {
        HStack {
            VStack(alignment: .leading, spacing: AppSpacing.xxSmall) {
                Text("Profile")
                    .font(.system(size: 24, weight: .bold))
                    .foregroundStyle(AppColors.textPrimary)
                
                Text("Your baby's information.")
                    .font(.system(size: 14, weight: .medium))
                    .foregroundStyle(AppColors.textSecondary)
            }
            Spacer()
            Button {} label: {
                Image(systemName: "gear.badge")
                    .font(.system(size: 17, weight: .semibold))
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
                        .font(.system(size: 40, weight: .bold))
                        .foregroundStyle(AppColors.surface)
                )
            
            VStack(alignment: .center, spacing: AppSpacing.xxSmall) {
                Text("Emma Rose")
                    .font(.system(size: 24, weight: .bold))
                    .foregroundStyle(AppColors.surface)
                Text("Born March 15, 2024")
                    .font(.system(size: 14, weight: .semibold))
                    .foregroundStyle(AppColors.surface)
            }
            Spacer()
            
            // Stats Row
            HStack(spacing: 30) {
                statItem(value: "11 weeks", label: "Age")
                Divider()
                statItem(value: "3.65 kg", label: "Weight")
                Divider()
                statItem(value: "58 cm", label: "Height")
            }
            Spacer()
        }
        .frame(maxWidth: .infinity)
        .padding(AppSpacing.medium)
        .background(
            LinearGradient(
                colors: [AppColors.lavander, AppColors.accent],
                startPoint: .topLeading,
                endPoint: .bottomTrailing)
        )
        .cornerRadius(20)
    }
    
    private func statItem(value: String, label: String) -> some View {
        VStack(spacing: AppSpacing.xxSmall) {
            Text(value)
                .font((.system(size: 18, weight: .bold)))
                .foregroundStyle(AppColors.surface)
            Text(label)
                .font((.system(size: 14, weight: .semibold)))
                .foregroundStyle(AppColors.surface)
        }
    }
}
