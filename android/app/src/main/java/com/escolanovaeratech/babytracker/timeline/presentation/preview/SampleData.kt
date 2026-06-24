package com.escolanovaeratech.babytracker.timeline.presentation.preview

import androidx.compose.ui.graphics.Color
import com.escolanovaeratech.babytracker.timeline.domain.TimelineEventType
import com.escolanovaeratech.babytracker.timeline.presentation.TimelineEventUiModel

// ─── Sample data ─────────────────────────────────────────────────────────────

internal val sampleEvents = listOf(
    TimelineEventUiModel(
        id = "0",
        type = TimelineEventType.BOTTLE_FEEDING,
        title = "Bottle Feeding",
        formattedTime = "2:30 PM",
        subtitle = "🍼 120 ml   🕐 15 min"
    ),
    TimelineEventUiModel(
        id = "1",
        type = TimelineEventType.WOKE_UP,
        title = "Woke Up",
        formattedTime = "2:00 PM",
        subtitle = "💤 Slept for 2h 15min"
    ),
    TimelineEventUiModel(
        id = "2",
        type = TimelineEventType.DIAPER_CHANGE,
        title = "Diaper Change",
        formattedTime = "1:45 PM",
        subtitle = "\uD83D\uDCA7 Pee",
        //tagColor = Color(0xFFB3E5FC)
    ),
    TimelineEventUiModel(
        id = "3",
        type = TimelineEventType.FELL_ASLEEP,
        title = "Fell Asleep",
        formattedTime = "11:45 AM",
        subtitle = "🌙 Nap time"
    ),
    TimelineEventUiModel(
        id = "4",
        type = TimelineEventType.BREASTFEEDING,
        title = "Breastfeeding",
        formattedTime = "11:15 AM",
        subtitle = "Left side   🕐 20 min"
    ),
    TimelineEventUiModel(
        id = "5",
        type = TimelineEventType.BATH_TIME,
        title = "Bath Time",
        formattedTime = "10:00 AM",
        subtitle = "🛁 Warm bath"
    ),
    TimelineEventUiModel(
        id = "6",
        type = TimelineEventType.DIAPER_CHANGE,
        title = "Diaper Change",
        formattedTime = "9:30 AM",
        subtitle = "\uD83D\uDCA9 Poop",
        //tagColor = Color(0xFFFFCC80)
    )
)