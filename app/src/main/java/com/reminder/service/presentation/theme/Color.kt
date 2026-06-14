package com.reminder.service.presentation.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

// Light theme teal colors
private val Teal600 = Color(0xFF00897B)
private val Teal100 = Color(0xFFB2DFDB)
private val Teal950 = Color(0xFF003733)
private val Teal300 = Color(0xFF4DB6AC)
private val Teal200 = Color(0xFF80CBC4)
private val Teal50 = Color(0xFFE0F2F1)
private val Teal40 = Color(0xFFB2DFDB)

// Dark theme teal colors
private val Teal800 = Color(0xFF00695C)
private val Teal900 = Color(0xFF004D40)
private val Teal950Dark = Color(0xFF00251A)
private val Teal700 = Color(0xFF00796B)

// Tertiary accent colors (coral/lime for balance)
private val Coral300 = Color(0xFFFFAB91)
private val Brown950 = Color(0xFF2A0C05)
private val Orange200 = Color(0xFFFFD180)
private val Black = Color(0xFF1E0700)

// Surface colors
private val BlueGrey100 = Color(0xFFDADFE0)
private val BlueGrey700 = Color(0xFF3F4946)
private val DarkTealBackground = Color(0xFF111514)
private val LightTealBackground = Color(0xFFFAFDFC)

// Error colors
private val Red700 = Color(0xFFBA1A1A)
private val White = Color(0xFFFFFFFF)
private val Red100 = Color(0xFFFFDAD6)
private val Red950 = Color(0xFF410002)
private val Red900 = Color(0xFF93000A)

/**
 * Light color scheme with modern teal palette
 */
val LightColorScheme = lightColorScheme(
    primary = Teal600,
    onPrimary = White,
    primaryContainer = Teal100,
    onPrimaryContainer = Teal950,

    secondary = Teal300,
    onSecondary = Teal950,
    secondaryContainer = Teal200,
    onSecondaryContainer = Teal950,

    tertiary = Coral300,
    onTertiary = Brown950,
    tertiaryContainer = Orange200,
    onTertiaryContainer = Black,

    error = Red700,
    onError = White,
    errorContainer = Red100,
    onErrorContainer = Red950,

    background = LightTealBackground,
    onBackground = Teal950,

    surface = LightTealBackground,
    onSurface = Teal950,
    surfaceVariant = BlueGrey100,
    onSurfaceVariant = BlueGrey700,

    outline = Teal700,
    outlineVariant = Teal200,

    inverseSurface = Teal950,
    inverseOnSurface = Teal50,
)

/**
 * Dark color scheme with modern teal palette
 */
val DarkColorScheme = darkColorScheme(
    primary = Teal200,
    onPrimary = Teal950,
    primaryContainer = Teal800,
    onPrimaryContainer = Teal50,

    secondary = Teal300,
    onSecondary = Teal950,
    secondaryContainer = Teal700,
    onSecondaryContainer = Teal50,

    tertiary = Coral300,
    onTertiary = Brown950,
    tertiaryContainer = Orange200,
    onTertiaryContainer = Black,

    error = Red900,
    onError = Teal950,
    errorContainer = Red700,
    onErrorContainer = White,

    background = DarkTealBackground,
    onBackground = Teal40,

    surface = DarkTealBackground,
    onSurface = Teal40,
    surfaceVariant = BlueGrey700,
    onSurfaceVariant = BlueGrey100,

    outline = Teal700,
    outlineVariant = Teal800,

    inverseSurface = Teal40,
    inverseOnSurface = Teal950,
)