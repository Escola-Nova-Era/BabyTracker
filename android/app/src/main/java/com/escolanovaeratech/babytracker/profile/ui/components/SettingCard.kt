package com.example.componentetelaperfil.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.componentetelaperfil.ui.theme.BabyTrackerTheme
import com.example.componentetelaperfil.R
import com.example.componentetelaperfil.ui.theme.CardShapeLarge
import com.example.componentetelaperfil.ui.theme.SurfaceColor

@Composable
fun SettingsCard() {
    Card(
        shape = CardShapeLarge,
        colors = CardDefaults.cardColors(containerColor = SurfaceColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column {
            SettingsItem(
                title = "Edit baby profile",
                icon = R.drawable.ic_account,
                subtitle = "Update name, birth date and info",
                brush = gradientOrange,
                iconTint = SurfaceColor
            )
            HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
            SettingsItem(
                title = "Notifications",
                icon = R.drawable.ic_notifications,
                subtitle = "Sleep and feeding reminders" ,
                brush = gradientBlue,
                iconTint = SurfaceColor
            )
            HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
            SettingsItem(
                title = "Export data",
                icon = R.drawable.ic_privacy,
                subtitle = "Download tracking history",
                brush = gradientGreen,
                iconTint = SurfaceColor
            )
            HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
            SettingsItem(
                title = "Help & Support",
                icon = R.drawable.ic_help,
                subtitle = "Get help and contact support" ,
                brush = gradientPurple,
                iconTint = SurfaceColor

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsCardPreview() {
    BabyTrackerTheme() {
        SettingsCard()
    }
}