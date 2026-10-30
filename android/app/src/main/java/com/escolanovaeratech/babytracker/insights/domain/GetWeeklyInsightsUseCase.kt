package com.escolanovaeratech.babytracker.insights.domain

import com.escolanovaeratech.babytracker.data.local.EventEntity
import com.escolanovaeratech.babytracker.data.local.EventType
import com.escolanovaeratech.babytracker.insights.ui.InsightsUiState
import com.escolanovaeratech.babytracker.insights.ui.components.ChangingData
import com.escolanovaeratech.babytracker.insights.ui.components.FeedingData
import com.escolanovaeratech.babytracker.insights.ui.components.SleepData
import java.util.Calendar

/**
 * Use Case responsável pela agregação e cálculo das métricas semanais de alimentação,
 * sono e trocas de fraldas para a tela de Insights.
 */
class GetWeeklyInsightsUseCase {

    operator fun invoke(
        events: List<EventEntity>,
        weekDays: List<String>
    ): InsightsUiState {
        val feedingMap = weekDays.associateWith { 0f }.toMutableMap()
        val sleepMinutesMap = weekDays.associateWith { 0 }.toMutableMap()
        val diaperMap = weekDays.associateWith { 0 }.toMutableMap()

        for (event in events) {
            val dayIndex = getDayOfWeekIndex(event.timestamp)
            val day = if (dayIndex in weekDays.indices) weekDays[dayIndex] else weekDays.firstOrNull().orEmpty()
            when (event.type) {
                EventType.FEEDING -> {
                    val amount = event.amountMl ?: 0
                    feedingMap[day] = (feedingMap[day] ?: 0f) + amount.toFloat()
                }
                EventType.SLEEP -> {
                    val duration = event.durationMinutes ?: 0
                    sleepMinutesMap[day] = (sleepMinutesMap[day] ?: 0) + duration
                }
                EventType.DIAPER -> {
                    diaperMap[day] = (diaperMap[day] ?: 0) + 1
                }
                else -> { /* Outros tipos de evento não entram nos três gráficos principais */ }
            }
        }

        val feedingList = weekDays.map { day ->
            FeedingData(dayOfWeek = day, amountMl = feedingMap[day] ?: 0f)
        }

        val sleepingList = weekDays.map { day ->
            val minutes = sleepMinutesMap[day] ?: 0
            val hours = minutes / 60f
            SleepData(dayOfWeek = day, hours = hours)
        }

        val changingList = weekDays.map { day ->
            ChangingData(dayOfWeek = day, count = diaperMap[day] ?: 0)
        }

        val activeFeeding = feedingList.map { it.amountMl }.filter { it > 0f }
        val avgMl = if (activeFeeding.isNotEmpty()) activeFeeding.average().toFloat() else 0f

        val activeSleep = sleepingList.map { it.hours }.filter { it > 0f }
        val avgHours = if (activeSleep.isNotEmpty()) activeSleep.average().toFloat() else 0f

        val activeDiapers = changingList.map { it.count }.filter { it > 0 }
        val avgChanges = if (activeDiapers.isNotEmpty()) activeDiapers.average().toFloat() else 0f

        return InsightsUiState(
            feedingDataList = feedingList,
            sleepingDataList = sleepingList,
            changingDataList = changingList,
            averageMl = avgMl,
            averageHours = avgHours,
            averageChanges = avgChanges
        )
    }

    private fun getDayOfWeekIndex(timestamp: Long): Int {
        val calendar = Calendar.getInstance().apply { timeInMillis = timestamp }
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> 0
            Calendar.TUESDAY -> 1
            Calendar.WEDNESDAY -> 2
            Calendar.THURSDAY -> 3
            Calendar.FRIDAY -> 4
            Calendar.SATURDAY -> 5
            Calendar.SUNDAY -> 6
            else -> 0
        }
    }
}
