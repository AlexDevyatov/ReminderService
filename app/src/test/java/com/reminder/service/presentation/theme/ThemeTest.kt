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
        assertEquals("Light primary should be teal 500", expectedPrimaryTeal, primary)

        // Verify emerald secondary (complementary)
        val expectedSecondaryEmerald = Color(0xFF2E7D32)
        assertEquals("Light secondary should be emerald 500", expectedSecondaryEmerald, secondary)

        // Verify amber tertiary (vibrant accent)
        val expectedTertiary = Color(0xFFFFB300)
        assertEquals("Light tertiary should be amber 500", expectedTertiary, tertiary)

        // Verify background
        val expectedBackground = Color(0xFFFAFDFC)
        assertEquals("Light background should be very light neutral", expectedBackground, background)

        // Verify error
        val expectedError = Color(0xFFBA1A1A)
        assertEquals("Light error should be red", expectedError, error)
    }



    @Test
    fun `light theme should have proper on-colors for contrast`() {
        val onPrimary = LightColorScheme.onPrimary
        val onSecondary = LightColorScheme.onSecondary
        val onTertiary = LightColorScheme.onTertiary
        val onBackground = LightColorScheme.onBackground
        val onError = LightColorScheme.onError

        // Verify onPrimary is white
        assertEquals("Light onPrimary should be white", Color(0xFFFFFFFF), onPrimary)

        // Verify onError is white
        assertEquals("Light onError should be white", Color(0xFFFFFFFF), onError)

        // Verify dark on-colors for light backgrounds
        val expectedDarkText = Color(0xFF001F1F)
        assertEquals("Light onBackground should be dark neutral", expectedDarkText, onBackground)
    }



    @Test
    fun `theme should have all surface variants defined`() {
        val surface = LightColorScheme.surface
        val surfaceVariant = LightColorScheme.surfaceVariant
        val outline = LightColorScheme.outline

        assertNotNull("Surface should be defined", surface)
        assertNotNull("SurfaceVariant should be defined", surfaceVariant)
        assertNotNull("Outline should be defined", outline)
    }
}