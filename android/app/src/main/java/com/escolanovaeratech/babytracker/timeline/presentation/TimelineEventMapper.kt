package com.escolanovaeratech.babytracker.timeline.presentation

import com.escolanovaeratech.babytracker.timeline.domain.*
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.*

private val timelineTimeFormat = LocalDateTime.Format {
    hour()
    char(':')
    minute()
}

fun TimelineEventModel.toUiModel(): TimelineEventUiModel {
    return TimelineEventUiModel(
        id = id,
        type = type,
        title = type.toDisplayTitle(),
        formattedTime = occurredAt.format(timelineTimeFormat),
        subtitle = description,
    )
}

private fun TimelineEventType.toDisplayTitle(): String {
    return when (this) {
        TimelineEventType.BOTTLE_FEEDING -> "Bottle Feeding"
        TimelineEventType.WOKE_UP -> "Woke Up"
        TimelineEventType.DIAPER_CHANGE -> "Diaper Change"
        TimelineEventType.FELL_ASLEEP -> "Fell Asleep"
        TimelineEventType.BREASTFEEDING -> "Breastfeeding"
        TimelineEventType.BATH_TIME -> "Bath Time"
    }
}