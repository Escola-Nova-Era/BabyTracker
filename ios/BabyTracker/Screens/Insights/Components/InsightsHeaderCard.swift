import SwiftUI

struct InsightsHeaderCard: View {
    var body: some View {
        VStack(alignment: .leading, spacing: AppSpacing.xSmall) {
            Text("Great Progress!")
                .font(.system(size: 22, weight: .bold))
                .foregroundStyle(.white)
            Text("Emma's pattems are developping beautifull. Her feedind and sleep routines show healthy consistency.")
                .font(.system(size: 14, weight: .regular))
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
        .cornerRadius(20)
    }
}

#Preview {
    InsightsHeaderCard()
}
