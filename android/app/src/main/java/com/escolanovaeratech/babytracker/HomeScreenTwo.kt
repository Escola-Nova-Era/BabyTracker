package com.escolanovaeratech.babytracker

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DashboardCard(
    title: String,
    value: String,
    subtitle: String,
    backgroundColor: Color,
    iconResId: Int,
    iconColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Coluna da Esquerda: Textos
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
                Column {
                    Text(
                        text = value,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = subtitle,
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
            }

            // Lado Direito: O Ícone estilizado com fundo circular branco
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.White, shape = RoundedCornerShape(50)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// 2. O GRID QUE JUNTA OS 4 CARDS (A SEÇÃO COMPLETA)
@Composable
fun SummaryGrid() {
    val defaultAndroidIcon = R.drawable.ic_menu_info_details

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título da Seção
        Text(
            text = "TODAY'S SUMMARY",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // Primeira Linha (Alimentação e Fralda)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DashboardCard(
                title = "Last Feeding",
                value = "2h ago",
                subtitle = "120 ml",
                backgroundColor = Color(0xFFE3F2FD),
                iconResId = defaultAndroidIcon,
                iconColor = Color(0xFF2196F3),
                modifier = Modifier.weight(1f)
            )
            DashboardCard(
                title = "Last Diaper",
                value = "45m ago",
                subtitle = "Pee",
                backgroundColor = Color(0xFFE8F5E9),
                iconResId = defaultAndroidIcon,
                iconColor = Color(0xFF4CAF50),
                modifier = Modifier.weight(1f)
            )
        }

        // Segunda Linha (Sono e Banho)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DashboardCard(
                title = "Sleep Status",
                value = "Awake",
                subtitle = "3h 20 min",
                backgroundColor = Color(0xFFF3E5F5),
                iconResId = defaultAndroidIcon,
                iconColor = Color(0xFF9C27B0),
                modifier = Modifier.weight(1f)
            )
            DashboardCard(
                title = "Last Bath",
                value = "Yesterday",
                subtitle = "7:30 PM",
                backgroundColor = Color(0xFFFFFDE7),
                iconResId = defaultAndroidIcon,
                iconColor = Color(0xFFFBC02D),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true, name = "Resumo de Hoje")
@Composable
fun SummaryGridPreview() {
    MaterialTheme {
        SummaryGrid()
    }
}