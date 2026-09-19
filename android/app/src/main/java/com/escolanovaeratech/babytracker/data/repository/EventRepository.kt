package com.escolanovaeratech.babytracker.data.repository

import com.escolanovaeratech.babytracker.data.local.EventDao
import com.escolanovaeratech.babytracker.data.local.EventEntity
import com.escolanovaeratech.babytracker.data.local.EventType
import kotlinx.coroutines.flow.Flow

/**
 * Repositório central para operações de leitura e persistência de eventos do bebê.
 * Desacopla a camada de apresentação (ViewModels) do Room DAO.
 */
class EventRepository(
    private val eventDao: EventDao
) {

    fun observeAll(): Flow<List<EventEntity>> = eventDao.observeAll()

    fun observeAllEvents(): Flow<List<EventEntity>> = eventDao.observeAll()

    fun observeByType(type: EventType): Flow<List<EventEntity>> = eventDao.observeByType(type)

    suspend fun getById(id: Long): EventEntity? = eventDao.getById(id)

    suspend fun insert(event: EventEntity): Long = eventDao.insert(event)

    suspend fun insertEvent(event: EventEntity): Long = eventDao.insert(event)

    suspend fun update(event: EventEntity) = eventDao.update(event)

    suspend fun delete(event: EventEntity) = eventDao.delete(event)

    suspend fun clear() = eventDao.clear()
}
