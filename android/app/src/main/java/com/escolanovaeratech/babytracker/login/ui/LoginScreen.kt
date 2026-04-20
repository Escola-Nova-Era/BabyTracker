package com.escolanovaeratech.babytracker.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.theme.BabyTrackingTheme
import com.escolanovaeratech.babytracker.theme.PrimaryLight
import com.escolanovaeratech.babytracker.theme.SecondaryLight
import com.escolanovaeratech.babytracker.theme.TextPrimary
import com.escolanovaeratech.babytracker.theme.TextSecondary

@Composable
fun LoginScreen() {
    // 1. Definindo os gradientes
    // Gradiente suave de fundo (Rosa claro para Branco)
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF5C8EE), // Rosa bem clarinho
            Color.White
        ),
        startY = 0f,
        endY = 600f // O gradiente termina no primeiro terço da tela
    )

    // Gradiente azul/roxo para o ícone (baseado na nossa paleta do Color.kt)
    val brandGradient = Brush.linearGradient(
        colors = listOf(PrimaryLight, SecondaryLight)
    )

    // 2. Box Raiz: Ocupa toda a tela e aplica o fundo
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        // 3. Column: Empilha os itens verticalmente e permite rolagem
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Centraliza os itens.
        ) {

            // --- INÍCIO DO HEADER ---

            // Ícone com fundo gradiente e bordas arredondadas
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(brandGradient)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baby_logo),
                    contentDescription = "Baby Tracker Logo",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Título Principal
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtítulo
            Text(
                text = "Join us on this beautiful journey",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )

            // --- FIM DO HEADER ---

        }
    }
}

// 4. Preview: Para vermos o resultado sem rodar o app no emulador
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    BabyTrackingTheme {
        LoginScreen()
    }
}