package com.escolanovaeratech.babytracker.profile.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.theme.*

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
                title = stringResource(R.string.settings_edit_profile_title),
                icon = R.drawable.ic_account,
                subtitle = stringResource(R.string.settings_edit_profile_sub),
                brush = GradientOrange,
                iconTint = SurfaceColor
            )
            HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
            SettingsItem(
                title = stringResource(R.string.settings_notifications_title),
                icon = R.drawable.ic_notifications,
                subtitle = stringResource(R.string.settings_notifications_sub),
                brush = GradientBlue,
                iconTint = SurfaceColor
            )
            HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
            SettingsItem(
                title = stringResource(R.string.settings_export_data_title),
                icon = R.drawable.ic_privacy,
                subtitle = stringResource(R.string.settings_export_data_sub),
                brush = GradientGreen,
                iconTint = SurfaceColor
            )
            HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.outlineVariant)
            SettingsItem(
                title = stringResource(R.string.settings_help_title),
                icon = R.drawable.ic_help,
                subtitle = stringResource(R.string.settings_help_sub),
                brush = GradientPurple,
                iconTint = SurfaceColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsCardPreview() {
    BabyTrackerTheme {
        SettingsCard()
    }
}