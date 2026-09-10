package com.escolanovaeratech.babytracker.insights.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.insights.ui.components.ChangingCard
import com.escolanovaeratech.babytracker.insights.ui.components.ChangingData
import com.escolanovaeratech.babytracker.insights.ui.components.FeedingCard
import com.escolanovaeratech.babytracker.insights.ui.components.FeedingData
import com.escolanovaeratech.babytracker.insights.ui.components.ProgressCard
import com.escolanovaeratech.babytracker.insights.ui.components.SleepData
import com.escolanovaeratech.babytracker.insights.ui.components.SleepingCard
import com.escolanovaeratech.babytracker.theme.AppTypography
import com.escolanovaeratech.babytracker.theme.BabyTrackerTheme
import com.escolanovaeratech.babytracker.theme.HomeBackgroundGradient
import com.escolanovaeratech.babytracker.theme.SurfaceDark

@Composable
fun InsightsScreen(
    modifier: Modifier = Modifier,
    feedingDataList: List<FeedingData> = emptyList(),
    sleepingDataList: List<SleepData> = emptyList(),
    changingDataList: List<ChangingData> = emptyList(),
    averageHours : Float = 0f,
    averageMl: Float = 0f,
    averageChanges: Float = 0f
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = HomeBackgroundGradient),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            // HEADER
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.insightsHeader),
                            style = AppTypography.headlineLarge,
                            color = SurfaceDark,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.insightsSubtitle),
                            style = AppTypography.bodyLarge,
                            color = SurfaceDark.copy(alpha = 0.7f)
                        )
                    }
                }
            }
            item { ProgressCard() }
            item {
                FeedingCard(
                    feedingDataList = feedingDataList,
                    averageMl = averageMl
                )
            }
            item {
                SleepingCard(
                sleepingDataList = sleepingDataList,
                averageHours = averageHours
                )
            }
            item { ChangingCard(
                changingDataList = changingDataList,
                averageChanges = averageChanges,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InsightsScreenPreview() {
    // Dados de exemplo para o Preview
    val sampleFeedingData = listOf(
        FeedingData("Mon", 450f),
        FeedingData("Tue", 520f),
        FeedingData("Wed", 480f),
        FeedingData("Thu", 600f),
        FeedingData("Fri", 550f),
        FeedingData("Sat", 700f),
        FeedingData("Sun", 620f)
    )
    val sampleSleepData = listOf(
        SleepData("Mon", 14.5f),
        SleepData("Tue", 13.0f),
        SleepData("Wed", 15.2f),
        SleepData("Thu", 12.8f),
        SleepData("Fri", 14.0f),
        SleepData("Sat", 16.0f),
        SleepData("Sun", 13.5f)
    )
    val sampleChangingData = listOf(
        ChangingData("Mon", 8),
        ChangingData("Tue", 6),
        ChangingData("Wed", 9),
        ChangingData("Thu", 7),
        ChangingData("Fri", 10),
        ChangingData("Sat", 8),
        ChangingData("Sun", 7)
    )
    val averageFeed = sampleFeedingData.map { it.amountMl }.average().toFloat()
    val averageHours = sampleSleepData.map { it.hours }.average().toFloat()
    val averageChanges = sampleChangingData.map { it.count }.average().toFloat()

    BabyTrackerTheme {
        InsightsScreen(
            feedingDataList = sampleFeedingData,
            averageMl = averageFeed,
            sleepingDataList = sampleSleepData,
            averageHours = averageHours,
            changingDataList = sampleChangingData,
            averageChanges = averageChanges
        )
    }
}