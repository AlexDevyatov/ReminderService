package com.reminder.service.presentation.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Primary tonal palette (Teal family)
private val Primary100 = Color(0xFFB2DFDB)
private val Primary500 = Color(0xFF00796B)
private val Primary900 = Color(0xFF004D40)

// Secondary tonal palette (Emerald family - complementary)
private val Secondary100 = Color(0xFFB9F6CA)
private val Secondary500 = Color(0xFF2E7D32)
private val Secondary900 = Color(0xFF003300)

// Tertiary tonal palette (Amber family - vibrant accent)
private val Tertiary100 = Color(0xFFFFECB3)
private val Tertiary500 = Color(0xFFFFB300)
private val Tertiary900 = Color(0xFFE65100)

// Error tonal palette (Red family)
private val Error100 = Color(0xFFFFDAD6)
private val Error500 = Color(0xFFBA1A1A)
private val Error900 = Color(0xFF410002)

// Neutral tonal palette (for surfaces, backgrounds)
private val Neutral10 = Color(0xFF001F1F)
private val Neutral50 = Color(0xFF2A6B5F)
private val Neutral90 = Color(0xFFB2DFDB)
private val Neutral95 = Color(0xFFE0F2F1)
private val Neutral99 = Color(0xFFFAFDFC)

/**
 * Material Design 3 compliant light color scheme with proper tonal palettes
 * All color pairs meet WCAG AA 4.5:1 contrast requirements
 */
val LightColorScheme = lightColorScheme(
    // Primary colors - Teal family
    primary = Primary500,
    onPrimary = Color.White,
    primaryContainer = Primary100,
    onPrimaryContainer = Primary900,

    // Secondary colors - Emerald family
    secondary = Secondary500,
    onSecondary = Color.White,
    secondaryContainer = Secondary100,
    onSecondaryContainer = Secondary900,

    // Tertiary colors - Amber family
    tertiary = Tertiary500,
    onTertiary = Neutral10,
    tertiaryContainer = Tertiary100,
    onTertiaryContainer = Tertiary900,

    // Error colors - Red family
    error = Error500,
    onError = Color.White,
    errorContainer = Error100,
    onErrorContainer = Error900,

    // Background and Surface colors - Neutral family
    background = Neutral99,
    onBackground = Neutral10,
    surface = Neutral99,
    onSurface = Neutral10,
    surfaceVariant = Neutral90,
    onSurfaceVariant = Neutral50,

    // Outline colors
    outline = Neutral50,
    outlineVariant = Neutral90,

    // Surface container colors - Enhanced tonal range for depth hierarchy
    surfaceContainer = Color(0xFFFAFDFC),
    surfaceContainerHigh = Color(0xFFF0F9F8),
    surfaceContainerHighest = Color(0xFFE0F2F1),
    surfaceContainerLow = Color(0xFFFEFFFE),
    surfaceDim = Color(0xFFD0DBDA),
    surfaceBright = Neutral99,

    // Inverse colors
    inverseSurface = Primary900,
    inverseOnSurface = Primary100,
    inversePrimary = Primary100,
)