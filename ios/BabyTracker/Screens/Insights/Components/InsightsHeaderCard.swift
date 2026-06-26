import SwiftUI

struct InsightsHeaderCard: View {
    var body: some View {
        VStack(alignment: .leading, spacing: AppSpacing.xSmall) {
            Text("Great Progress!")
                .font(AppTypography.title)
                .foregroundStyle(.white)
            Text("Emma's pattems are developping beautifull. Her feedind and sleep routines show healthy consistency.")
                .font(AppTypography.body)
                .foregroundStyle(.white)
        }
        .padding(AppSpacing.large)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(
            LinearGradient(
                colors: [AppColors.lavander, AppColors.primarySoft],
                startPoint: .leading,
                endPoint: .trailing
            )
        )
        .cornerRadius(AppTheme.cornerRadius)
    }
}
