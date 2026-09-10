package com.escolanovaeratech.babytracker.insights.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.theme.AppTypography
import com.escolanovaeratech.babytracker.theme.BabyTrackerTheme
import com.escolanovaeratech.babytracker.theme.CardShapeLarge
import com.escolanovaeratech.babytracker.theme.ChartBar3
import com.escolanovaeratech.babytracker.theme.SurfaceColor
import com.escolanovaeratech.babytracker.theme.SurfaceDark

data class ChangingData(
    val dayOfWeek: String,
    val count: Int
)

@Composable
fun ChangingCard(
    changingDataList: List<ChangingData>,
    averageChanges: Float,
    modifier: Modifier = Modifier
) {
    Card(
        shape = CardShapeLarge,
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp, 16.dp, 20.dp, 0.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = SurfaceColor)
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header e Legenda
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Daily Diaper Changes",
                        style = AppTypography.headlineLarge,
                        color = SurfaceDark.copy(alpha = 0.9f),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Last 7 days",
                        style = AppTypography.bodySmall,
                        color = SurfaceDark.copy(alpha = 0.7f)
                    )
                }

                // Gráfico de Barras de Trocas
                ChangingBarChart(
                    data = changingDataList,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                )

                // Summary Inferior
                Text(
                    text = stringResource(R.string.summary_diaper) + "%.1f".format(averageChanges) + stringResource(
                        R.string.summary_diaper_2
                    ),
                    style = AppTypography.bodyMedium,
                    color = SurfaceDark.copy(alpha = 0.6f),
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun ChangingBarChart(
    data: List<ChangingData>,
    modifier: Modifier = Modifier,
    barColor: Color = ChartBar3,
    maxCount: Float = 12f // Escala até 12 trocas diárias
) {
    val yAxisSteps = listOf(12, 9, 6, 3, 0)

    Row(modifier = modifier) {
        // Eixo Y (Valores na esquerda)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            yAxisSteps.forEach { step ->
                Text(
                    text = "$step",
                    style = AppTypography.labelSmall,
                    color = SurfaceDark.copy(alpha = 0.6f),
                    fontSize = 10.sp
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                val width = size.width
                val height = size.height

                // Grid de fundo
                val stepsCount = yAxisSteps.size - 1
                for (i in 0..stepsCount) {
                    val y = (height / stepsCount) * i
                    drawLine(
                        color = SurfaceDark.copy(alpha = 0.1f),
                        start = Offset(0f, y),
                        end = Offset(width, y),
                        strokeWidth = 1.dp.toPx()
                    )
                }

                val itemCount = data.size
                if (itemCount == 0) return@Canvas

                val spacePerItem = width / itemCount
                val barWidth = 14.dp.toPx()
                val cornerRadius = CornerRadius(12.dp.toPx(), 12.dp.toPx())

                data.forEachIndexed { index, item ->
                    val xOffset = (spacePerItem * index) + (spacePerItem / 2) - (barWidth / 2)
                    val barHeight = (item.count.toFloat().coerceAtMost(maxCount) / maxCount) * height
                    val yOffset = height - barHeight

                    // Fundo da barra
                    drawRoundRect(
                        color = barColor.copy(alpha = 0.15f),
                        topLeft = Offset(xOffset, 0f),
                        size = Size(65f, height),
                        cornerRadius = cornerRadius
                    )

                    // Barra preenchida
                    drawRoundRect(
                        color = barColor,
                        topLeft = Offset(xOffset, yOffset),
                        size = Size(65f , barHeight),
                        cornerRadius = cornerRadius
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Legenda dos dias da semana no eixo X
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                data.forEach { item ->
                    Text(
                        text = item.dayOfWeek.take(3),
                        style = AppTypography.labelSmall,
                        color = SurfaceDark.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangingCardPreview() {
    val sampleChangingData = listOf(
        ChangingData("Mon", 8),
        ChangingData("Tue", 6),
        ChangingData("Wed", 9),
        ChangingData("Thu", 7),
        ChangingData("Fri", 10),
        ChangingData("Sat", 8),
        ChangingData("Sun", 7)
    )

    BabyTrackerTheme {
        ChangingCard(
            changingDataList = sampleChangingData,
            averageChanges = sampleChangingData.map { it.count }.average().toFloat()
        )
    }
}
