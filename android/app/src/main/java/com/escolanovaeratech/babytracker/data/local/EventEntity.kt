package com.escolanovaeratech.babytracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Tipos de eventos que podem ser registrados na timeline do bebê.
 */
enum class EventType {
    FEEDING,   // Mamada / mamadeira
    SLEEP,     // Sono
    DIAPER,    // Troca de fralda
    BATH,      // Banho
    OTHER
}

/**
 * Representa um evento registrado na timeline do bebê.
 */
@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val type: EventType,

    val title: String,

    /** Momento do evento em epoch millis (UTC). */
    val timestamp: Long,

    val notes: String? = null,

    /** Quantidade em ml (ex.: mamadeira). */
    val amountMl: Int? = null,

    /** Duração do evento em minutos (ex.: sono, mamada). */
    val durationMinutes: Int? = null,
)
