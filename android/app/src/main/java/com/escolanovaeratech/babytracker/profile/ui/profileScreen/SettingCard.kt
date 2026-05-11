package com.escolanovaeratech.babytracker.profile.ui.profileScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.ui.theme.BabyTrackerTheme

@Composable
fun SettingsCard() {
    Card(
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
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
                iconTint = tintOrange
            )
            HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
            SettingsItem(
                title = "Notifications",
                icon = R.drawable.ic_notifications,
                subtitle = "Sleep and feeding reminders" ,
                brush = gradientBlue,
                iconTint = tintBlue
            )
            HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
            SettingsItem(
                title = "Export data",
                icon = R.drawable.ic_privacy,
                subtitle = "Download tracking history",
                brush = gradientGreen,
                iconTint = tintGreen
            )
            HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
            SettingsItem(
                title = "Help & Support",
                icon = R.drawable.ic_help,
                subtitle = "Get help and contact support" ,
                brush = gradientPurple,
                iconTint = tintPurple
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