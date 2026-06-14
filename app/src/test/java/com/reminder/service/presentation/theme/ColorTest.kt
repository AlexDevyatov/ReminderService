package com.reminder.service.presentation.theme

import org.junit.Test
import org.junit.Assert.*
import androidx.compose.ui.graphics.Color

class ColorTest {

    @Test
    fun `light color scheme should have teal primary color`() {
        val primary = LightColorScheme.primary
        assertEquals("Primary should be teal", Color(0xFF00897B), primary)
    }

    @Test
    fun `light color scheme should have proper primary container`() {
        val primaryContainer = LightColorScheme.primaryContainer
        assertEquals("PrimaryContainer should be light teal", Color(0xFFB2DFDB), primaryContainer)
    }

    @Test
    fun `light color scheme should have teal secondary color`() {
        val secondary = LightColorScheme.secondary
        assertEquals("Secondary should be teal 300", Color(0xFF4DB6AC), secondary)
    }

    @Test
    fun `dark color scheme should have teal primary color`() {
        val primary = DarkColorScheme.primary
        assertEquals("Primary should be lighter teal for dark theme", Color(0xFF80CBC4), primary)
    }

    @Test
    fun `dark color scheme should have dark background`() {
        val background = DarkColorScheme.background
        assertEquals("Background should be dark teal", Color(0xFF111514), background)
    }

    @Test
    fun `light color scheme should have tertiary accent color`() {
        val tertiary = LightColorScheme.tertiary
        assertEquals("Tertiary should be coral accent", Color(0xFFFFAB91), tertiary)
    }

    @Test
    fun `dark color scheme should have tertiary accent color`() {
        val tertiary = DarkColorScheme.tertiary
        assertEquals("Tertiary should be coral accent for dark theme", Color(0xFFFFAB91), tertiary)
    }

    @Test
    fun `light color scheme should have error color`() {
        val error = LightColorScheme.error
        assertEquals("Error should be red", Color(0xFFBA1A1A), error)
    }

    @Test
    fun `dark color scheme should have error color`() {
        val error = DarkColorScheme.error
        assertEquals("Error should be red for dark theme", Color(0xFF93000A), error)
    }
}