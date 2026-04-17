package com.escolanovaeratech.babytracker.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Esquema de cores para Light Mode (padrão) - Baby Tracking
 */
private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,           // #7C5ACE (roxo)
    onPrimary = TextOnPrimary,        // #FFFFFF (branco)
    primaryContainer = PrimaryLight,  // #9575CD (roxo claro)
    onPrimaryContainer = PrimaryDark, // #5E35B1 (roxo escuro)

    secondary = SecondaryColor,       // #4DB8A8 (turquesa)
    onSecondary = TextOnPrimary,      // #FFFFFF
    secondaryContainer = SecondaryLight, // #80CBC4 (turquesa clara)
    onSecondaryContainer = SecondaryDark, // #00897B (turquesa escura)

    tertiary = AccentGreen,           // #66BB6A (verde)
    onTertiary = TextOnPrimary,       // #FFFFFF

    error = ErrorColor,               // #B00020 (vermelho)
    onError = TextOnPrimary,          // #FFFFFF
    errorContainer = Color(0xFFF9DEDC), // Fundo de erro suave
    onErrorContainer = ErrorColor,    // Texto sobre fundo de erro

    background = BackgroundLight,     // #FAFAFA (cinza muito claro)
    onBackground = TextPrimary,       // #2C2C2C (cinza escuro)

    surface = SurfaceColor,           // #FFFFFF (branco)
    onSurface = TextPrimary,          // #2C2C2C
    surfaceVariant = Color(0xFFF5F5F5), // Variação de superfície
    onSurfaceVariant = TextSecondary, // #757575

    outline = BorderColor,            // #BDBDBD
    outlineVariant = DividerColor,    // #E0E0E0

    scrim = Color(0x00000000)         // Transparente para overlays
)

/**
 * Esquema de cores para Dark Mode
 */
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryLight,           // #9575CD (roxo claro para dark mode)
    onPrimary = TextOnPrimary,        // #FFFFFF
    primaryContainer = PrimaryDark,   // #5E35B1
    onPrimaryContainer = PrimaryLight, // #9575CD

    secondary = SecondaryLight,       // #80CBC4 (turquesa clara)
    onSecondary = Color(0xFF001F1A), // Texto escuro sobre turquesa
    secondaryContainer = SecondaryDark, // #00897B
    onSecondaryContainer = SecondaryLight, // #80CBC4

    tertiary = AccentGreen,           // #66BB6A
    onTertiary = Color(0xFF002202),   // Texto escuro sobre verde

    error = Color(0xFFFFB4AB),        // Rosa/vermelho claro para dark
    onError = Color(0xFF690005),      // Texto escuro sobre erro
    errorContainer = Color(0xFF93000A), // Fundo de erro em dark
    onErrorContainer = Color(0xFFFFB4AB), // Texto sobre fundo de erro

    background = BackgroundDark,      // #121212 (preto)
    onBackground = Color(0xFFE0E0E0), // Texto claro

    surface = SurfaceDark,            // #1E1E1E
    onSurface = Color(0xFFE0E0E0),   // Texto claro
    surfaceVariant = Color(0xFF424242), // Variação de superfície
    onSurfaceVariant = Color(0xFFBDBDBD), // Texto sobre variação

    outline = Color(0xFF9E9E9E),      // Bordas
    outlineVariant = DividerColorDark, // #424242

    scrim = Color(0xFF000000)         // Preto para overlays
)

/**
 * Tema principal da Baby Tracking
 *
 * @param darkTheme Se true, usa o esquema de cores escuro
 * @param dynamicColor Se true, usa cores dinâmicas do Android 12+ (ignorado por padrão)
 */
@Composable
fun BabyTrackingTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}