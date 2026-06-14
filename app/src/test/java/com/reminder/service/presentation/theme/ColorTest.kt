package com.reminder.service.presentation.theme

import org.junit.Test
import org.junit.Assert.*
import androidx.compose.ui.graphics.Color

class ColorTest {

    @Test
    fun `color scheme should have teal primary color`() {
        val primary = LightColorScheme.primary
        assertEquals("Primary should be teal 500", Color(0xFF00897B), primary)
    }

    @Test
    fun `color scheme should have proper primary container`() {
        val primaryContainer = LightColorScheme.primaryContainer
        assertEquals("PrimaryContainer should be light teal", Color(0xFFB2DFDB), primaryContainer)
    }

    @Test
    fun `color scheme should have emerald secondary color`() {
        val secondary = LightColorScheme.secondary
        assertEquals("Secondary should be emerald 500", Color(0xFF2E7D32), secondary)
    }

    @Test
    fun `color scheme should have tertiary accent color`() {
        val tertiary = LightColorScheme.tertiary
        assertEquals("Tertiary should be amber accent", Color(0xFFFFB300), tertiary)
    }

    @Test
    fun `color scheme should have error color`() {
        val error = LightColorScheme.error
        assertEquals("Error should be red", Color(0xFFBA1A1A), error)
    }
}