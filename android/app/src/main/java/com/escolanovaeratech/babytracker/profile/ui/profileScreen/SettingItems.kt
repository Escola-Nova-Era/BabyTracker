package com.escolanovaeratech.babytracker.profile.ui.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.ui.theme.BabyTrackerTheme

// Defined here as they are missing from Color.kt but used in SettingsItem and SettingsCard
val gradientOrange = Brush.linearGradient(listOf(Color(0xFFFF9A8B), Color(0xFFFF6A88)))
val tintOrange = Color(0xFFFF6A88)
val gradientBlue = Brush.linearGradient(listOf(Color(0xFF8BC6EC), Color(0xFF9599E2)))
val tintBlue = Color(0xFF9599E2)
val gradientGreen = Brush.linearGradient(listOf(Color(0xFF81FBB8), Color(0xFF28C76F)))
val tintGreen = Color(0xFF28C76F)
val gradientPurple = Brush.linearGradient(listOf(Color(0xFFCE9FFC), Color(0xFF7367F0)))
val tintPurple = Color(0xFF7367F0)

@Composable
fun IconWithBackground(
    painter: Painter,
    contentDescription: String?,
    size: Dp,
    brush: Brush,
    iconTint: Color
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(brush),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            tint = Color.White, // Using white for better contrast on background
            modifier = Modifier.size(size * 0.6f)
        )
    }
}

@Composable
fun SettingsItem(
    title: String,
    subtitle: String,
    icon: Int,
    brush: Brush,
    iconTint: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {}
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconWithBackground(
            painter = painterResource(id = icon),
            contentDescription = title,
            size = 40.dp,
            brush = brush,
            iconTint = iconTint
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SettingsItemPreview() {
    BabyTrackerTheme {
        SettingsItem(
            title = "Account",
            icon = R.drawable.ic_account,
            subtitle = "example",
            brush = gradientGreen,
            iconTint = tintGreen
        )
    }
}