package com.escolanovaeratech.babytracker.timeline.presentation

import com.escolanovaeratech.babytracker.timeline.domain.TimelineEventType

data class TimelineEventUiModel(
    val id: String,
    val type: TimelineEventType,
    val title: String,
    val formattedTime: String,
    val subtitle: String,
)