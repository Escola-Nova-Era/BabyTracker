package com.escolanovaeratech.babytracker.profile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.painter.Painter

// Paleta de cores do fundo dos icones do SettingItens.kt
val gradientOrange = Brush.linearGradient(listOf(Color(0xFFFEE6CF), Color(0xFFF6B18A)))
val gradientBlue = Brush.linearGradient(listOf(Color(0xFFCFE7FF), Color(0xFF7FB8FF)))
val gradientGreen = Brush.linearGradient(listOf(Color(0xFFCEF7DE), Color(0xFF79C18F)))
val gradientPurple = Brush.linearGradient(listOf(Color(0xFFF6DDFF), Color(0xFFB36BDD)))
@Composable
fun IconWithBackground(
    painter: Painter,
    contentDescription: String?,
    size: Dp = 40.dp,
    brush: Brush,
    iconTint: Color,
    cornerRadius: Dp = 8.dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(cornerRadius))
            .background(brush = brush),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            tint = iconTint,
            modifier = Modifier.size(size * 0.55f)
        )
    }
}
