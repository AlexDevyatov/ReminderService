package com.reminder.service.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import org.junit.Test
import org.junit.Assert.*

class ThemeTest {

    @Test
    fun `light color scheme should contain all required teal colors`() {
        val primary = LightColorScheme.primary
        val secondary = LightColorScheme.secondary
        val tertiary = LightColorScheme.tertiary
        val background = LightColorScheme.background
        val error = LightColorScheme.error

        // Verify teal primary
        val expectedPrimaryTeal = Color(0xFF00897B)
        assertEquals("Light primary should be teal 600", expectedPrimaryTeal, primary)

        // Verify teal secondary
        val expectedSecondaryTeal = Color(0xFF4DB6AC)
        assertEquals("Light secondary should be teal 300", expectedSecondaryTeal, secondary)

        // Verify tertiary accent (coral)
        val expectedTertiary = Color(0xFFFFAB91)
        assertEquals("Light tertiary should be coral 300", expectedTertiary, tertiary)

        // Verify background
        val expectedBackground = Color(0xFFFAFDFC)
        assertEquals("Light background should be very light teal", expectedBackground, background)

        // Verify error
        val expectedError = Color(0xFFBA1A1A)
        assertEquals("Light error should be red 700", expectedError, error)
    }

    @Test
    fun `dark color scheme should contain all required teal colors`() {
        val primary = DarkColorScheme.primary
        val secondary = DarkColorScheme.secondary
        val tertiary = DarkColorScheme.tertiary
        val background = DarkColorScheme.background
        val error = DarkColorScheme.error

        // Verify light teal primary for dark theme
        val expectedPrimaryTeal = Color(0xFF80CBC4)
        assertEquals("Dark primary should be light teal", expectedPrimaryTeal, primary)

        // Verify teal secondary
        val expectedSecondaryTeal = Color(0xFF4DB6AC)
        assertEquals("Dark secondary should be teal 300", expectedSecondaryTeal, secondary)

        // Verify tertiary accent (coral)
        val expectedTertiary = Color(0xFFFFAB91)
        assertEquals("Dark tertiary should be coral 300", expectedTertiary, tertiary)

        // Verify dark background
        val expectedBackground = Color(0xFF111514)
        assertEquals("Dark background should be dark teal", expectedBackground, background)

        // Verify error
        val expectedError = Color(0xFF93000A)
        assertEquals("Dark error should be red 900", expectedError, error)
    }

    @Test
    fun `light theme should have proper on-colors for contrast`() {
        val onPrimary = LightColorScheme.onPrimary
        val onSecondary = LightColorScheme.onSecondary
        val onTertiary = LightColorScheme.onTertiary
        val onBackground = LightColorScheme.onBackground
        val onError = LightColorScheme.onError

        // Verify onPrimary is white (light on teal)
        assertEquals("Light onPrimary should be white", Color(0xFFFFFFFF), onPrimary)

        // Verify onError is white
        assertEquals("Light onError should be white", Color(0xFFFFFFFF), onError)

        // Verify dark on-colors for light backgrounds
        val expectedDarkText = Color(0xFF003733)
        assertEquals("Light onBackground should be dark teal", expectedDarkText, onBackground)
    }

    @Test
    fun `dark theme should have proper on-colors for contrast`() {
        val onPrimary = DarkColorScheme.onPrimary
        val onSecondary = DarkColorScheme.onSecondary
        val onTertiary = DarkColorScheme.onTertiary
        val onBackground = DarkColorScheme.onBackground
        val onError = DarkColorScheme.onError

        // Verify onPrimary is dark teal (light text on light teal)
        val expectedDarkText = Color(0xFF003733)
        assertEquals("Dark onPrimary should be dark teal", expectedDarkText, onPrimary)

        // Verify onBackground is light teal
        val expectedLightText = Color(0xFFB2DFDB)
        assertEquals("Dark onBackground should be light teal", expectedLightText, onBackground)
    }

    @Test
    fun `both themes should have all surface variants defined`() {
        // Light theme
        val lightSurface = LightColorScheme.surface
        val lightSurfaceVariant = LightColorScheme.surfaceVariant
        val lightOutline = LightColorScheme.outline

        assertNotNull("Light surface should be defined", lightSurface)
        assertNotNull("Light surfaceVariant should be defined", lightSurfaceVariant)
        assertNotNull("Light outline should be defined", lightOutline)

        // Dark theme
        val darkSurface = DarkColorScheme.surface
        val darkSurfaceVariant = DarkColorScheme.surfaceVariant
        val darkOutline = DarkColorScheme.outline

        assertNotNull("Dark surface should be defined", darkSurface)
        assertNotNull("Dark surfaceVariant should be defined", darkSurfaceVariant)
        assertNotNull("Dark outline should be defined", darkOutline)
    }
}