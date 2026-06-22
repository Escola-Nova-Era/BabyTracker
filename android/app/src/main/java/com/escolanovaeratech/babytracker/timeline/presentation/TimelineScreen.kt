package com.escolanovaeratech.babytracker.timeline.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ─── Data model ──────────────────────────────────────────────────────────────

enum class TimelineEventType {
    BOTTLE_FEEDING,
    WOKE_UP,
    DIAPER_CHANGE,
    FELL_ASLEEP,
    BREASTFEEDING,
    BATH_TIME
}

data class TimelineEvent(
    val type: TimelineEventType,
    val title: String,
    val time: String,
    val subtitle: String? = null,
    val tag: String? = null,
    val tagColor: Color? = null
)

// ─── Sample data ─────────────────────────────────────────────────────────────

private val sampleEvents = listOf(
    TimelineEvent(
        type = TimelineEventType.BOTTLE_FEEDING,
        title = "Bottle Feeding",
        time = "2:30 PM",
        subtitle = "🍼 120 ml   🕐 15 min"
    ),
    TimelineEvent(
        type = TimelineEventType.WOKE_UP,
        title = "Woke Up",
        time = "2:00 PM",
        subtitle = "💤 Slept for 2h 15min"
    ),
    TimelineEvent(
        type = TimelineEventType.DIAPER_CHANGE,
        title = "Diaper Change",
        time = "1:45 PM",
        tag = "Pee",
        tagColor = Color(0xFFB3E5FC)
    ),
    TimelineEvent(
        type = TimelineEventType.FELL_ASLEEP,
        title = "Fell Asleep",
        time = "11:45 AM",
        subtitle = "🌙 Nap time"
    ),
    TimelineEvent(
        type = TimelineEventType.BREASTFEEDING,
        title = "Breastfeeding",
        time = "11:15 AM",
        subtitle = "Left side   🕐 20 min"
    ),
    TimelineEvent(
        type = TimelineEventType.BATH_TIME,
        title = "Bath Time",
        time = "10:00 AM",
        subtitle = "🛁 Warm bath"
    ),
    TimelineEvent(
        type = TimelineEventType.DIAPER_CHANGE,
        title = "Diaper Change",
        time = "9:30 AM",
        tag = "Poop",
        tagColor = Color(0xFFFFCC80)
    )
)

// ─── Icon helpers ─────────────────────────────────────────────────────────────

private fun eventIconEmoji(type: TimelineEventType): String = when (type) {
    TimelineEventType.BOTTLE_FEEDING  -> "🍼"
    TimelineEventType.WOKE_UP         -> "☀️"
    TimelineEventType.DIAPER_CHANGE   -> "👶"
    TimelineEventType.FELL_ASLEEP     -> "🌙"
    TimelineEventType.BREASTFEEDING   -> "🤱"
    TimelineEventType.BATH_TIME       -> "🛁"
}

private fun eventIconBg(type: TimelineEventType): Color = when (type) {
    TimelineEventType.BOTTLE_FEEDING  -> Color(0xFFFFF3E0)
    TimelineEventType.WOKE_UP         -> Color(0xFFFFF8E1)
    TimelineEventType.DIAPER_CHANGE   -> Color(0xFFE8F5E9)
    TimelineEventType.FELL_ASLEEP     -> Color(0xFFEDE7F6)
    TimelineEventType.BREASTFEEDING   -> Color(0xFFFFEBEE)
    TimelineEventType.BATH_TIME       -> Color(0xFFE3F2FD)
}

// ─── Screen ───────────────────────────────────────────────────────────────────

@Composable
fun TimelineScreen(
    date: String = "January 27, 2025",
    events: List<TimelineEvent> = sampleEvents
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FB))
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        TimelineHeader(date = date)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(events) { event ->
                TimelineEventCard(event = event)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

// ─── Header ──────────────────────────────────────────────────────────────────

@Composable
private fun TimelineHeader(date: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Today's Timeline",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A2E)
            )
            Text(
                text = date,
                fontSize = 13.sp,
                color = Color(0xFF9E9E9E)
            )
        }
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF1A1A2E)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = "Filter",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

// ─── Event card ───────────────────────────────────────────────────────────────

@Composable
private fun TimelineEventCard(event: TimelineEvent) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(horizontal = 14.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon bubble
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(eventIconBg(event.type)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = eventIconEmoji(event.type),
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Title + subtitle / tag
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = event.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1A1A2E)
            )
            Spacer(modifier = Modifier.height(2.dp))
            when {
                event.subtitle != null -> {
                    Text(
                        text = event.subtitle,
                        fontSize = 12.sp,
                        color = Color(0xFF9E9E9E)
                    )
                }
                event.tag != null -> {
                    EventTag(label = event.tag, color = event.tagColor ?: Color(0xFFE0E0E0))
                }
            }
        }

        // Time
        Text(
            text = event.time,
            fontSize = 12.sp,
            color = Color(0xFF9E9E9E),
            fontWeight = FontWeight.Medium
        )
    }
}

// ─── Tag pill ─────────────────────────────────────────────────────────────────

@Composable
private fun EventTag(label: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color)
            .padding(horizontal = 10.dp, vertical = 3.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF5D4037)
        )
    }
}

// ─── Preview ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, backgroundColor = 0xFFF8F9FB)
@Composable
fun TimelineScreenPreview() {
    MaterialTheme {
        TimelineScreen()
    }
}