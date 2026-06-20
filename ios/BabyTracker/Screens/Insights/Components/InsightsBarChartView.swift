import SwiftUI

struct InsightsBarChartView: View {
    let data: [Double]
    let labels: [String]
    let scale: [Int]
    let barColor: Color
    let chartHeight: CGFloat
    
    private enum Layout {
        static let sectionSpacing: CGFloat = 8
        static let axisToBarsSpacing: CGFloat = 10
        static let yAxisWidth: CGFloat = 40
        static let barGap: CGFloat = 10
        static let barColumnWidth: CGFloat = 24
        static let minBarHeight: CGFloat = 4
        static let xLabelsTopPadding: CGFloat = 6
        static let barCornerRadius: CGFloat = 4
    }

    var body: some View {
        VStack(spacing: Layout.sectionSpacing) {
            HStack(alignment: .bottom, spacing: Layout.axisToBarsSpacing) {
                // Eixo Y (números da escala)
                yAxis
                // Barras + rótulos do eixo X
                barsWithDays
            }
        }
    }
    
    private var yAxis: some View {
        VStack(alignment: .trailing, spacing: 0) {
            ForEach(scale.reversed(), id: \.self) { level in
                VStack(spacing: 0) {
                    Text("\(level)")
                        .font(AppTypography.overline)
                        .foregroundStyle(AppColors.textTertiary)
                    
                    if level != scale.first {
                        Spacer()
                    }
                    
                }
            }
        }
        .frame(width: Layout.yAxisWidth, height: chartHeight, alignment: .top)
    }
    
    private var barsWithDays: some View {
        let chartItems = Array(zip(labels, data))

        return VStack(spacing: 0) {
            // Barras proporcionais aos valores recebidos.
            HStack(alignment: .bottom, spacing: Layout.barGap) {
                ForEach(Array(chartItems.enumerated()), id: \.offset) { _, item in
                    RoundedRectangle(cornerRadius: Layout.barCornerRadius)
                        .fill(barColor)
                        .frame(width: Layout.barColumnWidth, height: barHeight(for: item.1))
                }
            }
            .frame(height: chartHeight, alignment: .bottom)

            // Rótulos do eixo X
            HStack(spacing: Layout.barGap) {
                ForEach(labels, id: \.self) { day in
                    Text(day)
                        .font(AppTypography.overline)
                        .foregroundStyle(AppColors.textTertiary)
                        .frame(width: Layout.barColumnWidth)
                }
            }
            .padding(.top, Layout.xLabelsTopPadding)
        }
    }
    
    private func barHeight(for value: Double) -> CGFloat {
        // Converter valor numérico em altura visual e respeitando o min
        let maxLevel = Double(scale.max() ?? 1)
        let percentage = value / maxLevel
        let height = (percentage * chartHeight)
        return max(Layout.minBarHeight, height)
    }
}

