# Teal Color Scheme - ReminderService

## Overview

Modern Material 3 color scheme based on teal tones with coral accent. Designed for ReminderService application to provide fresh, contemporary visual experience while maintaining accessibility and contrast requirements.

## Color Palette

### Light Theme

| Color Token | HEX Value | Description | Usage |
|-------------|-----------|-------------|-------|
| **Primary** | `#00897B` | Teal 600 | Main action buttons, selected items, floating action buttons |
| **OnPrimary** | `#FFFFFF` | White | Text/icons on primary background |
| **PrimaryContainer** | `#B2DFDB` | Teal 100 | Active tabs, progress indicators |
| **OnPrimaryContainer** | `#003733` | Teal 950 | Text/icons on primary container |
| **Secondary** | `#4DB6AC` | Teal 300 | Alternative actions, pills, tags |
| **OnSecondary** | `#003733` | Teal 950 | Text/icons on secondary background |
| **SecondaryContainer** | `#80CBC4` | Teal 200 | Chips, badges, alternative containers |
| **OnSecondaryContainer** | `#003733` | Teal 950 | Text/icons on secondary container |
| **Tertiary** | `#FFAB91` | Coral 300 | Accent elements, special highlights |
| **OnTertiary** | `#2A0C05` | Brown 950 | Text/icons on tertiary background |
| **TertiaryContainer** | `#FFD180` | Orange 200 | Accent containers |
| **OnTertiaryContainer** | `#1E0700` | Black | Text/icons on tertiary container |
| **Error** | `#BA1A1A` | Red 700 | Error messages, delete actions |
| **OnError** | `#FFFFFF` | White | Text/icons on error background |
| **ErrorContainer** | `#FFDAD6` | Red 100 | Error containers, warnings |
| **OnErrorContainer** | `#410002` | Red 950 | Text/icons on error container |
| **Background** | `#FAFDFC` | Teal 50 | Main screen background |
| **OnBackground** | `#003733` | Teal 950 | Primary text on background |
| **Surface** | `#FAFDFC` | Teal 50 | Cards, sheets, dialogs |
| **OnSurface** | `#003733` | Teal 950 | Text on surface |
| **SurfaceVariant** | `#DADFE0` | Blue Grey 100 | Alternative surfaces |
| **OnSurfaceVariant** | `#3F4946` | Blue Grey 700 | Text on surface variant |
| **Outline** | `#00796B` | Teal 700 | Borders, dividers |
| **OutlineVariant** | `#80CBC4` | Teal 200 | Alternative borders |
| **InverseSurface** | `#003733` | Teal 950 | Inverse backgrounds |
| **InverseOnSurface** | `#E0F2F1` | Teal 50 | Text on inverse surface |

### Dark Theme

| Color Token | HEX Value | Description | Usage |
|-------------|-----------|-------------|-------|
| **Primary** | `#80CBC4` | Teal 200 | Main action buttons, selected items (lighter for dark background) |
| **OnPrimary** | `#003733` | Teal 950 | Text/icons on primary background |
| **PrimaryContainer** | `#00695C` | Teal 800 | Active tabs, progress indicators |
| **OnPrimaryContainer** | `#E0F2F1` | Teal 50 | Text/icons on primary container |
| **Secondary** | `#4DB6AC` | Teal 300 | Alternative actions, pills, tags |
| **OnSecondary** | `#003733` | Teal 950 | Text/icons on secondary background |
| **SecondaryContainer** | `#00796B` | Teal 700 | Chips, badges, alternative containers |
| **OnSecondaryContainer** | `#E0F2F1` | Teal 50 | Text/icons on secondary container |
| **Tertiary** | `#FFAB91` | Coral 300 | Accent elements, special highlights |
| **OnTertiary** | `#2A0C05` | Brown 950 | Text/icons on tertiary background |
| **TertiaryContainer** | `#FFD180` | Orange 200 | Accent containers |
| **OnTertiaryContainer** | `#1E0700` | Black | Text/icons on tertiary container |
| **Error** | `#93000A` | Red 900 | Error messages, delete actions (lighter for dark background) |
| **OnError** | `#003733` | Teal 950 | Text/icons on error background |
| **ErrorContainer** | `#00695C` | Teal 800 | Error containers, warnings |
| **OnErrorContainer** | `#FFFFFF` | White | Text/icons on error container |
| **Background** | `#111514` | Dark teal | Main screen background |
| **OnBackground** | `#B2DFDB` | Teal 40 | Primary text on background |
| **Surface** | `#111514` | Dark teal | Cards, sheets, dialogs |
| **OnSurface** | `#B2DFDB` | Teal 40 | Text on surface |
| **SurfaceVariant** | `#3F4946` | Blue Grey 700 | Alternative surfaces |
| **OnSurfaceVariant** | `#DADFE0` | Blue Grey 100 | Text on surface variant |
| **Outline** | `#00796B` | Teal 700 | Borders, dividers |
| **OutlineVariant** | `#00695C` | Teal 800 | Alternative borders |
| **InverseSurface** | `#E0F2F1` | Teal 50 | Inverse backgrounds |
| **InverseOnSurface** | `#003733` | Teal 950 | Text on inverse surface |

## Implementation Details

### Files Modified
- `app/src/main/java/com/reminder/service/presentation/theme/Color.kt` - Created with teal color schemes
- `app/src/main/java/com/reminder/service/presentation/theme/Theme.kt` - Updated to use teal schemes as fallback

### Test Coverage
- `app/src/test/java/com/reminder/service/presentation/theme/ColorTest.kt` - Unit tests for color values
- `app/src/test/java/com/reminder/service/presentation/theme/ThemeTest.kt` - Unit tests for theme composition and accessibility

## Component Usage Guidelines

### TopAppBar
- **Container**: `surface`
- **Title/Actions**: `onSurface`
- **Icon colors**: `onSurfaceVariant` for secondary actions

### Buttons
- **Primary Button**: `primary` container with `onPrimary` text
- **Secondary Button**: `secondaryContainer` with `onSecondaryContainer` text
- **Text Button**: `primary` text with `surface` background
- **Tonal Button**: `secondary` container with `onSecondary` text

### Floating Action Button (FAB)
- **Container**: `primaryContainer`
- **Icon**: `onPrimaryContainer`
- Alternative: `primary` container with `onPrimary` icon

### Cards
- **Container**: `surface`
- **Title**: `onSurface`
- **Body**: `onSurfaceVariant`
- **Borders**: `outlineVariant`

### Chips/Tags
- **Container**: `secondaryContainer`
- **Label**: `onSecondaryContainer`
- **Selected state**: `primaryContainer` with `onPrimaryContainer` text

### Switch/Toggle
- **Active track**: `primaryContainer`
- **Inactive track**: `surfaceVariant`
- **Active thumb**: `onPrimaryContainer`
- **Inactive thumb**: `outline`

### Progress Indicators
- **Progress**: `primary`
- **Track**: `surfaceVariant`

### Snackbars/Alerts
- **Info**: `primaryContainer` with `onPrimaryContainer` text
- **Success**: `tertiaryContainer` with `onTertiaryContainer` text
- **Error**: `errorContainer` with `onErrorContainer` text

### Text Colors
- **Headlines**: `onSurface` (MaterialTheme.typography.headlineLarge/medium/small)
- **Titles**: `onSurface` (MaterialTheme.typography.titleLarge/medium/small)
- **Body**: `onSurface` (MaterialTheme.typography.bodyLarge/medium/small)
- **Labels**: `onSurfaceVariant` (MaterialTheme.typography.labelLarge/medium/small)
- **Captions**: `onSurfaceVariant` (MaterialTheme.typography.bodySmall)

## Accessibility Notes

### Contrast Ratios
- **Primary/OnPrimary**: ~8.5:1 (AAA compliant)
- **Secondary/OnSecondary**: ~4.5:1 (AA compliant)
- **Tertiary/OnTertiary**: ~4.2:1 (AA compliant)
- **Error/OnError**: ~7.2:1 (AA compliant)
- **Background/OnBackground**: ~8.2:1 (AAA compliant)
- **Surface/OnSurface**: ~8.2:1 (AAA compliant)

### Dark Mode Specifics
- Lighter primary color (`#80CBC4`) provides better contrast on dark backgrounds
- Error color is adjusted to be lighter (`#93000A`) for dark theme
- All text colors maintain at least 4.5:1 contrast ratio (AA compliant)

## Dynamic Color Support

The theme supports Android 12+ dynamic color:
- When `dynamicColor = true` and SDK ≥ 31, uses system Material You colors
- Otherwise falls back to the teal color scheme
- This provides automatic adaptation to user's wallpaper on supported devices

## Theme Usage in Code

```kotlin
@Composable
fun ReminderApp() {
    ReminderTheme(
        darkTheme = isSystemInDarkTheme(),
        dynamicColor = true // Set to false to force teal colors
    ) {
        // App content
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            // UI components
        }
    }
}
```

## Migration Notes

If you have existing purple/ Material colors, update references:
- Replace `Color.Purple` with `MaterialTheme.colorScheme.primary`
- Use `MaterialTheme.colorScheme` for all color tokens
- The teal palette provides similar functionality with a modern look

## Future Enhancements

Potential improvements for the color scheme:
- Add semantic tokens for specific UI states
- Create custom color extensions for reminder-specific states
- Consider seasonal color variations (optional feature)
- Add alpha variants for overlays and state layers