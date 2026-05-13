package com.escolanovaeratech.babytracker.profile.ui.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.componentetelaperfil.R
import com.example.componentetelaperfil.ui.theme.AppTypography
import com.example.componentetelaperfil.ui.theme.BabyTrackerTheme
import com.example.componentetelaperfil.ui.theme.SurfaceColor
import com.example.componentetelaperfil.ui.theme.SurfaceDark

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            // HEADER
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(end = 0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.profileLabel),
                            style = AppTypography.headlineLarge,
                            color = SurfaceDark,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.profileSubheader),
                            style = AppTypography.bodyLarge,
                            color = SurfaceDark.copy(alpha = 0.7f),
                        )
                    }

                    // ICON SETTINGS
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(color = SurfaceColor),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { /* click action */ }) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Settings",
                                tint = SurfaceDark,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // PROFILE CARD
            item { ProfileCard() }
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // PROFILE CARD
            item {
                Text(
                    text = stringResource(R.string.settingsLabel),
                    style = AppTypography.headlineSmall,
                    color = SurfaceDark.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            item { SettingsCard() }
        }
    }
}

// Gradient Colors
val backgroundBrush = Brush.linearGradient(
    colors = listOf(
        Color(0xFFF8E3FF),
        Color(0xF7FFFFFF),
        Color(0xFFD9EEFF)
    )
)
val backgroundCard = Brush.linearGradient(
    colors = listOf(
        Color(0xFF927FCC),
        Color(0xFF66BED0)
    )
)


@Preview
@Composable
fun ProfileScreenPreview (){
    BabyTrackingTheme {
        ProfileScreen()
    }
}