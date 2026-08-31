package com.escolanovaeratech.babytracker.timeline.presentation

import androidx.compose.ui.graphics.Color
import com.escolanovaeratech.babytracker.timeline.domain.TimelineEventType

internal val TimelineEventType.iconEmoji: String
    get() = when (this) {
        TimelineEventType.BOTTLE_FEEDING -> "🍼"
        TimelineEventType.WOKE_UP -> "☀️"
        TimelineEventType.DIAPER_CHANGE -> "👶"
        TimelineEventType.FELL_ASLEEP -> "🌙"
        TimelineEventType.BREASTFEEDING -> "🤱"
        TimelineEventType.BATH_TIME -> "🛁"
    }

internal val TimelineEventType.iconBackgroundColor: Color
    get() = when (this) {
        TimelineEventType.BOTTLE_FEEDING -> Color(0xFFFFF3E0)
        TimelineEventType.WOKE_UP -> Color(0xFFFFF8E1)
        TimelineEventType.DIAPER_CHANGE -> Color(0xFFE8F5E9)
        TimelineEventType.FELL_ASLEEP -> Color(0xFFEDE7F6)
        TimelineEventType.BREASTFEEDING -> Color(0xFFFFEBEE)
        TimelineEventType.BATH_TIME -> Color(0xFFE3F2FD)
    }