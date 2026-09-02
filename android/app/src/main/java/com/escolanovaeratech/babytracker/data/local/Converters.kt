package com.escolanovaeratech.babytracker.data.local

import androidx.room.TypeConverter

/**
 * Conversores de tipos que o Room não sabe persistir nativamente.
 */
class Converters {

    @TypeConverter
    fun fromEventType(type: EventType): String = type.name

    @TypeConverter
    fun toEventType(value: String): EventType = EventType.valueOf(value)
}
