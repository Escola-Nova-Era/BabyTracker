package com.escolanovaeratech.babytracker.timeline.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.theme.*
import com.escolanovaeratech.babytracker.timeline.data.TimelineItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TimelineScreen(
    modifier: Modifier = Modifier,
    viewModel: TimelineViewModel = viewModel(
        factory = TimelineViewModel.provideFactory(LocalContext.current.applicationContext)
    )
) {
    val uiState by viewModel.uiState.collectAsState()
    TimelineScreenContent(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
fun TimelineScreenContent(
    modifier: Modifier = Modifier,
    uiState: TimelineUiState
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = HomeBackgroundGradient)
    ) {
        when (uiState) {
            is TimelineUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = PrimaryColor)
                }
            }
            is TimelineUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp)
                ) {
                    // Header da Timeline
                    item {
                        TimelineHeader()
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    if (uiState.items.isEmpty()) {
                        item {
                            TimelineEmptyState()
                        }
                    } else {
                        // Lista de eventos da Timeline
                        itemsIndexed(uiState.items) { index, item ->
                            TimelineRow(
                                item = item,
                                isLastItem = index == uiState.items.lastIndex
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TimelineEmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 32.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(PrimaryColor.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.CalendarToday,
                contentDescription = null,
                tint = PrimaryColor,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.timeline_empty_title),
            style = AppTypography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.timeline_empty_subtitle),
            style = AppTypography.bodyMedium,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun TimelineHeader() {
    val currentDateFormatted = remember {
        SimpleDateFormat("MMMM d, yyyy", Locale.getDefault()).format(Date())
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Today's Timeline",
                style = AppTypography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = currentDateFormatted,
                style = AppTypography.bodyMedium,
                color = TextSecondary
            )
        }

        // Botão de filtro circular estilizado
        Surface(
            modifier = Modifier.size(42.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 2.dp
        ) {
            IconButton(
                onClick = { /* Filtrar atividades */ },
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Outlined.FilterList,
                    contentDescription = stringResource(R.string.filter),
                    tint = TextPrimary
                )
            }
        }
    }
}

@Composable
private fun TimelineRow(
    item: TimelineItem,
    isLastItem: Boolean
) {
    IntrinsicRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        // Coluna da Linha e Marcador Visual (Bubble + Dot)
        Box(
            modifier = Modifier
                .width(40.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.TopCenter
        ) {
            // Linha vertical conectando os eventos
            if (!isLastItem) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .offset(y = 18.dp)
                        .background(colorResource(R.color.timeline_line))
                )
            }

            // Bubble com o emoji/ícone da atividade
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(colorResource(item.bubbleColor)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.icon,
                    fontSize = 18.sp
                )
            }

            // Ponto indicador ao lado
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .offset(x = 18.dp, y = 14.dp)
                    .clip(CircleShape)
                    .background(colorResource(item.dotColor))
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Card com detalhes da atividade
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 4.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, colorResource(R.color.card_stroke))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Título e Horário
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.title,
                        style = AppTypography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E1E1E)
                    )
                    Text(
                        text = item.time,
                        style = AppTypography.bodySmall,
                        color = TextSecondary
                    )
                }

                // Subtítulo opcional (ex.: "Slept for 2h 15min")
                if (item.subtitle != null) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = item.subtitle,
                        style = AppTypography.bodyMedium,
                        color = TextSecondary
                    )
                }

                // Metadados adicionais (ex.: "↕ 120 ml", "◴ 15 min")
                if (item.metaPrimary != null || item.metaSecondary != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (item.metaPrimary != null) {
                            MetaPill(text = item.metaPrimary)
                        }
                        if (item.metaSecondary != null) {
                            MetaPill(text = item.metaSecondary)
                        }
                    }
                }

                // Tag opcional (ex.: "Pee", "Poop")
                if (item.tag != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(999.dp))
                            .background(colorResource(item.tagBackgroundColor))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = item.tag,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(item.tagTextColor)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MetaPill(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(R.color.meta_bg))
            .border(1.dp, colorResource(R.color.card_stroke), RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            color = TextSecondary
        )
    }
}

/**
 * Layout auxiliar com altura intrínseca para sincronizar a altura da linha do tempo com a do Card.
 */
@Composable
private fun IntrinsicRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier.height(IntrinsicSize.Min),
        content = content
    )
}

private fun buildMockTimeline(): List<TimelineItem> {
    return listOf(
        TimelineItem(
            title = "Bottle Feeding",
            time = "2:30 PM",
            icon = "\uD83C\uDF7C",
            bubbleColor = R.color.feed_bubble,
            dotColor = R.color.feed_dot,
            metaPrimary = "\u2195 120 ml",
            metaSecondary = "\u25F4 15 min"
        ),
        TimelineItem(
            title = "Woke Up",
            time = "2:00 PM",
            icon = "\u2600",
            bubbleColor = R.color.sleep_bubble,
            dotColor = R.color.sleep_dot,
            subtitle = "Slept for 2h 15min"
        ),
        TimelineItem(
            title = "Diaper Change",
            time = "1:45 PM",
            icon = "\uD83D\uDC76",
            bubbleColor = R.color.diaper_bubble,
            dotColor = R.color.diaper_dot,
            tag = "Pee",
            tagBackgroundColor = R.color.tag_blue_bg,
            tagTextColor = R.color.tag_blue_text
        ),
        TimelineItem(
            title = "Fell Asleep",
            time = "11:45 AM",
            icon = "\u263E",
            bubbleColor = R.color.sleep_bubble,
            dotColor = R.color.sleep_dot,
            subtitle = "Nap time"
        ),
        TimelineItem(
            title = "Breastfeeding",
            time = "11:15 AM",
            icon = "\u2665",
            bubbleColor = R.color.breast_bubble,
            dotColor = R.color.breast_dot,
            metaPrimary = "Left side",
            metaSecondary = "\u25F4 20 min"
        ),
        TimelineItem(
            title = "Bath Time",
            time = "10:00 AM",
            icon = "\uD83D\uDEC1",
            bubbleColor = R.color.bath_bubble,
            dotColor = R.color.bath_dot,
            subtitle = "Warm bath"
        ),
        TimelineItem(
            title = "Diaper Change",
            time = "9:30 AM",
            icon = "\uD83D\uDC76",
            bubbleColor = R.color.diaper_bubble,
            dotColor = R.color.diaper_dot,
            tag = "Poop"
        )
    )
}

@Preview(showBackground = true, name = "Timeline with Items")
@Composable
fun TimelineScreenPreview() {
    BabyTrackerTheme {
        TimelineScreenContent(
            uiState = TimelineUiState.Success(buildMockTimeline())
        )
    }
}

@Preview(showBackground = true, name = "Timeline Empty State")
@Composable
fun TimelineScreenEmptyPreview() {
    BabyTrackerTheme {
        TimelineScreenContent(
            uiState = TimelineUiState.Success(emptyList())
        )
    }
}