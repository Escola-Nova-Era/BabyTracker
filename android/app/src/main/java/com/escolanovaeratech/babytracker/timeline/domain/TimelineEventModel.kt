package com.escolanovaeratech.babytracker.timeline.domain

import kotlinx.datetime.LocalDateTime

enum class TimelineEventType {
    BOTTLE_FEEDING,
    WOKE_UP,
    DIAPER_CHANGE,
    FELL_ASLEEP,
    BREASTFEEDING,
    BATH_TIME
}

data class TimelineEventModel (
    val id: String,
    val type: TimelineEventType,
    val occurredAt: LocalDateTime,
    val description: String? = null,
    val amountMl: Int? = null
)