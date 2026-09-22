package com.escolanovaeratech.babytracker.util

import java.util.Calendar

/**
 * Configura o horário especificado mantendo a data atual e retorna o timestamp em milissegundos.
 */
fun Calendar.setTodayTime(hour: Int, minute: Int): Long {
    set(Calendar.HOUR_OF_DAY, hour)
    set(Calendar.MINUTE, minute)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
    return timeInMillis
}

/**
 * Retorna o epoch millis de hoje para a hora e minuto informados.
 */
fun getTimestampForToday(hour: Int, minute: Int): Long {
    return Calendar.getInstance().setTodayTime(hour, minute)
}

fun calculateDurationMinutes(
    startHour: Int,
    startMinute: Int,
    endHour: Int?,
    endMinute: Int?
): Int? {
    if (endHour == null || endMinute == null) return null
    val startTotalMinutes = startHour * 60 + startMinute
    var endTotalMinutes = endHour * 60 + endMinute
    if (endTotalMinutes < startTotalMinutes) {
        endTotalMinutes += 24 * 60
    }
    return (endTotalMinutes - startTotalMinutes).coerceAtLeast(0)
}