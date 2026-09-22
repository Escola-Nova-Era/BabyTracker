package com.escolanovaeratech.babytracker.insights.ui

import com.escolanovaeratech.babytracker.data.local.EventDao
import com.escolanovaeratech.babytracker.data.local.EventEntity
import com.escolanovaeratech.babytracker.data.local.EventType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Calendar

class InsightsViewModelTest {

    private class FakeEventDao : EventDao {
        val events = mutableListOf<EventEntity>()
        override fun observeAll(): Flow<List<EventEntity>> = flowOf(events)
        override fun observeByType(type: EventType): Flow<List<EventEntity>> = flowOf(events.filter { it.type == type })
        override suspend fun getById(id: Long): EventEntity? = events.find { it.id == id }
        override suspend fun insert(event: EventEntity): Long = 1L
        override suspend fun update(event: EventEntity) {}
        override suspend fun delete(event: EventEntity) {}
        override suspend fun clear() {}
    }

    @Test
    fun processEvents_emptyList_returnsZeroValues() {
        val fakeDao = FakeEventDao()
        val viewModel = InsightsViewModel(fakeDao)

        val state = viewModel.processEvents(emptyList())

        assertEquals(7, state.feedingDataList.size)
        assertEquals(7, state.sleepingDataList.size)
        assertEquals(7, state.changingDataList.size)
        assertEquals(0f, state.averageMl, 0.01f)
        assertEquals(0f, state.averageHours, 0.01f)
        assertEquals(0f, state.averageChanges, 0.01f)
    }

    @Test
    fun processEvents_aggregatesCorrectDayValues() {
        val fakeDao = FakeEventDao()
        val viewModel = InsightsViewModel(fakeDao)

        // Criar um timestamp de uma Segunda-feira
        val mondayCalendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            set(Calendar.HOUR_OF_DAY, 10)
        }
        val mondayTimestamp = mondayCalendar.timeInMillis

        val events = listOf(
            EventEntity(
                type = EventType.FEEDING,
                title = "Bottle",
                timestamp = mondayTimestamp,
                amountMl = 120
            ),
            EventEntity(
                type = EventType.FEEDING,
                title = "Bottle 2",
                timestamp = mondayTimestamp,
                amountMl = 150
            ),
            EventEntity(
                type = EventType.SLEEP,
                title = "Nap",
                timestamp = mondayTimestamp,
                durationMinutes = 120 // 2 horas
            ),
            EventEntity(
                type = EventType.DIAPER,
                title = "Diaper",
                timestamp = mondayTimestamp
            )
        )

        val state = viewModel.processEvents(events)

        val mondayFeeding = state.feedingDataList.first { it.dayOfWeek == "Mon" }
        assertEquals(270f, mondayFeeding.amountMl, 0.01f)
        assertEquals(270f, state.averageMl, 0.01f)

        val mondaySleep = state.sleepingDataList.first { it.dayOfWeek == "Mon" }
        assertEquals(2.0f, mondaySleep.hours, 0.01f)
        assertEquals(2.0f, state.averageHours, 0.01f)

        val mondayDiaper = state.changingDataList.first { it.dayOfWeek == "Mon" }
        assertEquals(1, mondayDiaper.count)
        assertEquals(1f, state.averageChanges, 0.01f)
    }
}
