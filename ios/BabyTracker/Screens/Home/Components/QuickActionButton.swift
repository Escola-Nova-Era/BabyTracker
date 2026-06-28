import SwiftUI

struct QuickActionButton: View {
    let item: ActionButton
    
    var body: some View {
        Button { }
        label: {
            VStack(spacing: AppSpacing.xSmall) {
                Image(systemName: item.icon)
                    .font(AppTypography.subheadlineBold)
                Text(item.title)
                    .font(AppTypography.subheadlineBold)
            }
            .foregroundStyle(.white)
            .padding(AppSpacing.medium)
            .frame(maxWidth: .infinity)
            .frame(height: 96)
            .background(item.colors)
            .cornerRadius(14)
        }
        .buttonStyle(.plain)
    }
}
