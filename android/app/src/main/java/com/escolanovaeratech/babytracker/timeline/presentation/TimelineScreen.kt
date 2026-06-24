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
import com.escolanovaeratech.babytracker.timeline.presentation.preview.sampleEvents


// ─── Screen ───────────────────────────────────────────────────────────────────

@Composable
fun TimelineScreen(
    date: String = "January 27, 2025",
    events: List<TimelineEventUiModel> = sampleEvents
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
private fun TimelineEventCard(event: TimelineEventUiModel) {
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
                .background(event.type.iconBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = event.type.iconEmoji,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Title + subtitle
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = event.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1A1A2E)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = event.subtitle,
                fontSize = 12.sp,
                color = Color(0xFF9E9E9E)
            )
        }

        // Time
        Text(
            text = event.formattedTime,
            fontSize = 12.sp,
            color = Color(0xFF9E9E9E),
            fontWeight = FontWeight.Medium
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