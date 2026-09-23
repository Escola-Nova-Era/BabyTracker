package com.escolanovaeratech.babytracker.insights.domain

import com.escolanovaeratech.babytracker.data.local.EventEntity
import com.escolanovaeratech.babytracker.data.local.EventType
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Calendar

class GetWeeklyInsightsUseCaseTest {

    private val useCase = GetWeeklyInsightsUseCase()
    private val englishWeekDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    private val ptWeekDays = listOf("Seg", "Ter", "Qua", "Qui", "Sex", "Sáb", "Dom")

    @Test
    fun invoke_emptyEvents_returnsEmptyStatsForEveryDay() {
        val result = useCase(emptyList(), englishWeekDays)

        assertEquals(7, result.feedingDataList.size)
        assertEquals(7, result.sleepingDataList.size)
        assertEquals(7, result.changingDataList.size)
        assertEquals(0f, result.averageMl, 0.01f)
        assertEquals(0f, result.averageHours, 0.01f)
        assertEquals(0f, result.averageChanges, 0.01f)
    }

    @Test
    fun invoke_withCustomLocalizedWeekdays_aggregatesCorrectly() {
        val wednesdayTimestamp = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY)
            set(Calendar.HOUR_OF_DAY, 14)
        }.timeInMillis

        val events = listOf(
            EventEntity(
                type = EventType.FEEDING,
                title = "Bottle",
                timestamp = wednesdayTimestamp,
                amountMl = 180
            ),
            EventEntity(
                type = EventType.SLEEP,
                title = "Nap",
                timestamp = wednesdayTimestamp,
                durationMinutes = 90
            ),
            EventEntity(
                type = EventType.DIAPER,
                title = "Pee",
                timestamp = wednesdayTimestamp
            )
        )

        val result = useCase(events, ptWeekDays)

        val wedFeeding = result.feedingDataList.first { it.dayOfWeek == "Qua" }
        assertEquals(180f, wedFeeding.amountMl, 0.01f)

        val wedSleep = result.sleepingDataList.first { it.dayOfWeek == "Qua" }
        assertEquals(1.5f, wedSleep.hours, 0.01f)

        val wedDiaper = result.changingDataList.first { it.dayOfWeek == "Qua" }
        assertEquals(1, wedDiaper.count)
    }
}
