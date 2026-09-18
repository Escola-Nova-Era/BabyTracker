package com.escolanovaeratech.babytracker.data.repository

import com.escolanovaeratech.babytracker.data.local.EventDao
import com.escolanovaeratech.babytracker.data.local.EventEntity
import com.escolanovaeratech.babytracker.data.local.EventType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class EventRepositoryTest {

    private class FakeEventDao : EventDao {
        val events = mutableListOf<EventEntity>()

        override fun observeAll(): Flow<List<EventEntity>> = flowOf(events.toList())

        override fun observeByType(type: EventType): Flow<List<EventEntity>> =
            flowOf(events.filter { it.type == type })

        override suspend fun getById(id: Long): EventEntity? = events.find { it.id == id }

        override suspend fun insert(event: EventEntity): Long {
            val id = (events.size + 1).toLong()
            events.add(event.copy(id = id))
            return id
        }

        override suspend fun update(event: EventEntity) {
            val index = events.indexOfFirst { it.id == event.id }
            if (index != -1) events[index] = event
        }

        override suspend fun delete(event: EventEntity) {
            events.removeAll { it.id == event.id }
        }

        override suspend fun clear() {
            events.clear()
        }
    }

    @Test
    fun insertAndObserveEvents_returnsSavedEvents() = runBlocking {
        val fakeDao = FakeEventDao()
        val repository = EventRepository(fakeDao)

        val feedingEvent = EventEntity(
            type = EventType.FEEDING,
            title = "Bottle Feeding",
            timestamp = System.currentTimeMillis(),
            amountMl = 150
        )

        val insertedId = repository.insert(feedingEvent)
        assertEquals(1L, insertedId)

        val allEvents = repository.observeAll().first()
        assertEquals(1, allEvents.size)
        assertEquals(EventType.FEEDING, allEvents[0].type)
        assertEquals(150, allEvents[0].amountMl)
    }

    @Test
    fun observeByType_filtersCorrectly() = runBlocking {
        val fakeDao = FakeEventDao()
        val repository = EventRepository(fakeDao)

        repository.insert(EventEntity(type = EventType.FEEDING, title = "Feeding", timestamp = 1000L))
        repository.insert(EventEntity(type = EventType.BATH, title = "Bath", timestamp = 2000L))

        val feedingOnly = repository.observeByType(EventType.FEEDING).first()
        assertEquals(1, feedingOnly.size)
        assertEquals(EventType.FEEDING, feedingOnly[0].type)

        val bathOnly = repository.observeByType(EventType.BATH).first()
        assertEquals(1, bathOnly.size)
        assertEquals(EventType.BATH, bathOnly[0].type)
    }
}
