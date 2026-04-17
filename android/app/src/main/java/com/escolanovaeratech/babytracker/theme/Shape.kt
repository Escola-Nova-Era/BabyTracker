package com.escolanovaeratech.babytracker.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Guia de uso:
 *
 * Telas principais (Home, Profile): displayLarge / headlineLarge
 * Títulos de seção (Today's Timeline, Insights): headlineMedium / headlineSmall
 * Títulos de cards (Bottle Feeding, Wake up): titleLarge
 * Texto principal (descrições de atividades): bodyMedium
 * Labels e pequenos textos (horários, datas): labelMedium / labelSmall
 * Hints e secundário: bodySmall
 */


val AppShapes = Shapes(
    // Pequenas formas - para pequenos componentes (buttons, chips)
    small = RoundedCornerShape(8.dp),

    // Médias - para componentes padrão (cards, dialogs)
    medium = RoundedCornerShape(12.dp),

    // Grandes - para componentes que ocupam mais espaço
    large = RoundedCornerShape(16.dp),

    // Extra large - para componentes que precisam de mais destaque
    extraLarge = RoundedCornerShape(20.dp)
)

/**
 * Formas especificas
 */

// Cards de atividades (Bottle Feeding, Wake up, etc)
val CardShapeSmall = RoundedCornerShape(12.dp)

// Cards maiores (Profile, Insights)
val CardShapeLarge = RoundedCornerShape(16.dp)

// Buttons primários
val ButtonShape = RoundedCornerShape(12.dp)

// FAB (Floating Action Button) - redondo
val FABShape = RoundedCornerShape(50.dp)

// Input fields
val InputShape = RoundedCornerShape(10.dp)

// Cards de timeline/atividades com borda superior arredondada
val TimelineCardShape = RoundedCornerShape(
    topStart = 12.dp,
    topEnd = 12.dp,
    bottomStart = 12.dp,
    bottomEnd = 12.dp
)