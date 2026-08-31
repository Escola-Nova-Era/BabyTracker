package com.escolanovaeratech.babytracker.timeline.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escolanovaeratech.babytracker.timeline.presentation.preview.sampleEvents
import com.escolanovaeratech.babytracker.timeline.presentation.components.*


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

// ─── Preview ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, backgroundColor = 0xFFF8F9FB)
@Composable
fun TimelineScreenPreview() {
    MaterialTheme {
        TimelineScreen()
    }
}