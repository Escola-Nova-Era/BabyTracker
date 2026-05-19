import SwiftUI

struct QuickActionButton: View {
    
    let item: ActionButton
    
    var body: some View {
        
        Button { }
        label: {
            VStack(spacing: AppSpacing.xSmall) {
                Image(systemName: item.icon)
                    .font(.system(size: 16, weight: .bold))
                Text(item.title)
                    .font(.system(size: 16, weight: .bold))
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
