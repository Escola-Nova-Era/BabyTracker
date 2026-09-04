package com.escolanovaeratech.babytracker.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.theme.*

@Composable
fun HomeScreenUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(brush = HomeBackgroundGradient)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // 1. CABEÇALHO COM SINO DE NOTIFICAÇÕES
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(R.string.home_greeting),
                    style = AppTypography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = stringResource(R.string.home_subtitle),
                    style = AppTypography.bodyMedium,
                    color = TextSecondary
                )
            }

            // Sino de Notificações Circular (como no Figma e Profile)
            Surface(
                modifier = Modifier.size(42.dp),
                shape = CircleShape,
                color = SurfaceColor,
                shadowElevation = 1.dp
            ) {
                IconButton(onClick = { /* Ação de notificação */ }) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notifications",
                        tint = TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // 2. CARD DO BEBÊ (FOTO + NOME + IDADE/PESO + EDIT)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = CardShapeLarge,
            colors = CardDefaults.cardColors(containerColor = SurfaceColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Foto do Bebê Circular com Fundo Suave
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFE8EC)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_baby),
                        contentDescription = stringResource(R.string.BabyName),
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                // Nome e Resumo (Idade / Peso)
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.BabyName),
                        style = AppTypography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = stringResource(R.string.baby_age_weight_summary),
                        style = AppTypography.bodySmall,
                        color = TextSecondary
                    )
                }

                // Botão de Editar Circular Suave
                Surface(
                    modifier = Modifier.size(36.dp),
                    shape = CircleShape,
                    color = AccentBlue.copy(alpha = 0.12f)
                ) {
                    IconButton(onClick = { /* Ação de editar perfil */ }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Profile",
                            tint = AccentBlue,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }


        // 3. TODAY'S SUMMARY (GRID 2x2)
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.todays_summary),
                style = AppTypography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = TextSecondary,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Linha 1: Feeding & Diaper
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SummaryCard(
                    title = stringResource(R.string.last_feeding),
                    time = stringResource(R.string.last_feeding_time),
                    detail = stringResource(R.string.last_feeding_detail),
                    iconRes = R.drawable.ic_bottle,
                    iconTint = AccentBlue,
                    iconBg = AccentBlue.copy(alpha = 0.12f),
                    modifier = Modifier.weight(1f)
                )

                SummaryCard(
                    title = stringResource(R.string.last_diaper),
                    time = stringResource(R.string.last_diaper_time),
                    detail = stringResource(R.string.last_diaper_detail),
                    iconRes = R.drawable.ic_heart,
                    iconTint = AccentGreen,
                    iconBg = AccentGreen.copy(alpha = 0.12f),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Linha 2: Sleep Status & Last Bath
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SummaryCard(
                    title = stringResource(R.string.sleep_status),
                    time = stringResource(R.string.sleep_status_time),
                    detail = stringResource(R.string.sleep_status_detail),
                    iconRes = R.drawable.ic_moon,
                    iconTint = PrimaryColor,
                    iconBg = PrimaryColor.copy(alpha = 0.12f),
                    modifier = Modifier.weight(1f)
                )

                SummaryCard(
                    title = stringResource(R.string.last_bath),
                    time = stringResource(R.string.last_bath_time),
                    detail = stringResource(R.string.last_bath_detail),
                    iconRes = R.drawable.ic_bath,
                    iconTint = AccentOrange,
                    iconBg = AccentOrange.copy(alpha = 0.12f),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // 4. QUICK ACTIONS (GRID 2x2)
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.quick_actions),
                style = AppTypography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = TextSecondary,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Linha 1: Add Feeding & Add Diaper
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionButton(
                    text = stringResource(R.string.add_feeding),
                    color = AccentBlue,
                    iconRes = R.drawable.ic_bottle,
                    modifier = Modifier.weight(1f)
                )

                ActionButton(
                    text = stringResource(R.string.add_diaper),
                    color = AccentGreen,
                    iconRes = R.drawable.ic_heart,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Linha 2: Sleep/Wake & Add Bath
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionButton(
                    text = stringResource(R.string.sleep_wake),
                    color = PrimaryColor,
                    iconRes = R.drawable.ic_moon,
                    modifier = Modifier.weight(1f)
                )

                ActionButton(
                    text = stringResource(R.string.add_bath),
                    color = AccentOrange,
                    iconRes = R.drawable.ic_bath,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

// --- COMPONENTE DO CARD DE RESUMO (TODAY'S SUMMARY) ---
@Composable
fun SummaryCard(
    title: String,
    time: String,
    detail: String,
    iconRes: Int,
    iconTint: Color,
    iconBg: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = CardShapeLarge,
        colors = CardDefaults.cardColors(containerColor = SurfaceColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Ícone do card em círculo suave
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = title,
                    tint = iconTint,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                style = AppTypography.bodySmall,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = time,
                style = AppTypography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = detail,
                style = AppTypography.bodySmall,
                color = TextSecondary.copy(alpha = 0.8f)
            )
        }
    }
}

// --- COMPONENTE DOS BOTÕES DE QUICK ACTION ---
@Composable
fun ActionButton(
    text: String,
    color: Color,
    iconRes: Int,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { },
        modifier = modifier.height(100.dp),
        shape = CardShapeLarge,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = TextOnPrimary
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(28.dp),
                tint = TextOnPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = text,
                style = AppTypography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextOnPrimary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenUIPreview() {
    BabyTrackerTheme {
        HomeScreenUI()
    }
}