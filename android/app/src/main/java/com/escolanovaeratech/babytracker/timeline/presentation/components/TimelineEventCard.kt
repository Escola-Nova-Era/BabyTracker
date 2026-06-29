package com.escolanovaeratech.babytracker.timeline.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.escolanovaeratech.babytracker.timeline.presentation.TimelineEventUiModel
import com.escolanovaeratech.babytracker.timeline.presentation.iconBackgroundColor
import com.escolanovaeratech.babytracker.timeline.presentation.iconEmoji

// ─── Event card ───────────────────────────────────────────────────────────────

@Composable
internal fun TimelineEventCard(event: TimelineEventUiModel) {
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