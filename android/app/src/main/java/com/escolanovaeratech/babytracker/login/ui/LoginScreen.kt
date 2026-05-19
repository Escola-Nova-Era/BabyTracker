package com.escolanovaeratech.babytracker.home.ui

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

// 1. O COMPONENTE REUTILIZÁVEL (O CARD)
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
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
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
            }
            // --- FIM DO HEADER ---

            Spacer(modifier = Modifier.height(32.dp))

            // --- INÍCIO DO CARTÃO BRANCO ---
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Ocupa toda a altura disponível
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                color = Color.White
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 24.dp, vertical = 32.dp)
                ) {

                    // 1. Campo: Parent Name
                    Text(
                        text = "Parent Name",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = parentName,
                        onValueChange = { parentName = it },
                        placeholder = { Text("Enter your name", color = TextHint) },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Person,
                                contentDescription = "Person Icon",
                                tint = TextSecondary
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = DividerColor,
                            focusedBorderColor = PrimaryLight,
                            unfocusedContainerColor = Color(0xFFF8F9FA),
                            focusedContainerColor = Color(0xFFF8F9FA)
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // 2. Campo: Email
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
                    .background(Color.White, shape = RoundedCornerShape(50))
                    .padding(6.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

// 2. O GRID QUE JUNTA OS 4 CARDS (A SEÇÃO COMPLETA)
@Composable
fun SummaryGrid() {
    // Usando uma imagem nativa do sistema Android para não depender de pacotes externos ou arquivos locais que possam falhar
    val defaultAndroidIcon = android.R.drawable.ic_menu_info_details

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
                backgroundColor = Color(0xFFE3F2FD), // Azul pastel
                iconResId = defaultAndroidIcon,
                iconColor = Color(0xFF2196F3),
                modifier = Modifier.weight(1f)
            )
            DashboardCard(
                title = "Last Diaper",
                value = "45m ago",
                subtitle = "Pee",
                backgroundColor = Color(0xFFE8F5E9), // Verde pastel
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
                backgroundColor = Color(0xFFF3E5F5), // Roxo pastel
                iconResId = defaultAndroidIcon,
                iconColor = Color(0xFF9C27B0),
                modifier = Modifier.weight(1f)
            )
            DashboardCard(
                title = "Last Bath",
                value = "Yesterday",
                subtitle = "7:30 PM",
                backgroundColor = Color(0xFFFFFDE7), // Amarelo pastel
                iconResId = defaultAndroidIcon,
                iconColor = Color(0xFFFBC02D),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// 3. A PREVIEW (O que desenha a interface na lateral do Android Studio)
@Preview(showBackground = true, name = "Resumo de Hoje")
@Composable
fun SummaryGridPreview() {
    androidx.compose.material3.MaterialTheme {
        SummaryGrid()
    }
}