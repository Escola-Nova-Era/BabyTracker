package com.example.componentetelaperfil.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.componentetelaperfil.R
import com.example.componentetelaperfil.ui.theme.BabyTrackerTheme
import com.example.componentetelaperfil.ui.theme.White

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
    BabyTrackerTheme() {
        SettingsItem(
            title = "Account",
            icon = R.drawable.ic_account,
            subtitle = "example",
            brush = gradientGreen,
            iconTint = White
        )
    }
}