package com.alaric.aigamerecommender.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.alaric.aigamerecommender.R

enum class SemantixThemeType {
    CYBERPUNK, RETRO_CRT, SLEEK_MODERN
}

// Map the raw colors to Material's Semantic Roles
private val CyberpunkColorScheme = darkColorScheme(
    primary = CyanGlow,
    secondary = NeonPink,
    background = DeepVoid,
    surface = SurfaceGrid,
    onBackground = OffWhite,
    onSurface = OffWhite
)

private val RetroColorScheme = darkColorScheme(
    primary = PhosphorGreen,
    background = CrtBlack,
    surface = CrtBlack,
    onBackground = PhosphorGreen,
    onSurface = PhosphorGreen
)

@Composable
fun SemantixTheme(
    themeType: SemantixThemeType = SemantixThemeType.CYBERPUNK, // Default to Cyberpunk
    content: @Composable () -> Unit
) {
    // 1. Select Material Colors
    val colorScheme = when (themeType) {
        SemantixThemeType.CYBERPUNK -> CyberpunkColorScheme
        SemantixThemeType.RETRO_CRT -> RetroColorScheme
        SemantixThemeType.SLEEK_MODERN -> darkColorScheme() // Fallback to standard M3 dark
    }

    // 2. Select Custom Structural Tokens
    val borders = when (themeType) {
        SemantixThemeType.CYBERPUNK -> SemantixBorders(thickness = 2.dp, glowRadius = 8.dp, glowColor = CyanGlow)
        SemantixThemeType.RETRO_CRT -> SemantixBorders(thickness = 1.dp, glowColor = PhosphorGreen)
        SemantixThemeType.SLEEK_MODERN -> SemantixBorders(thickness = 0.dp)
    }

    val spacing = when (themeType) {
        SemantixThemeType.CYBERPUNK -> SemantixSpacing(cardPadding = 20.dp, itemSpacing = 16.dp)
        SemantixThemeType.RETRO_CRT -> SemantixSpacing(cardPadding = 12.dp, itemSpacing = 4.dp)
        SemantixThemeType.SLEEK_MODERN -> SemantixSpacing(cardPadding = 16.dp, itemSpacing = 12.dp)
    }

     val cyberpunkGrid = ImageBitmap.imageResource(id = R.drawable.ic_cyberpunknavbar)
     val cyberBrush = remember(cyberpunkGrid) {
         ShaderBrush(ImageShader(cyberpunkGrid, TileMode.Repeated, TileMode.Repeated))
     }

    val backgrounds = when (themeType) {
        SemantixThemeType.CYBERPUNK -> SemantixBackgrounds(
            // screen = cyberBrush,
            screen = SolidColor(DeepVoid), // Fallback
            surface = SolidColor(SurfaceGrid),
            navBar = cyberBrush,
           // navBar = SolidColor(DeepVoid)
        )
        SemantixThemeType.RETRO_CRT -> SemantixBackgrounds(
            screen = SolidColor(CrtBlack),
            surface = SolidColor(Color.Black),
            navBar = SolidColor(Color.Black)
        )
        SemantixThemeType.SLEEK_MODERN -> SemantixBackgrounds(
            screen = SolidColor(OffWhite),
            surface = SolidColor(Color.White),
            navBar = SolidColor(OffWhite)
        )
    }

    // 3. Update Status Bar Color
    val view = LocalView.current
    if (!view.isInEditMode) { // in case of @Preview
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    // 4. Wrap everything in the Providers
    CompositionLocalProvider(
        LocalSemantixBorders provides borders,
        LocalSemantixSpacing provides spacing,
        LocalSemantixBackgrounds provides backgrounds
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography, // Provided by Type.kt
            content = content
        )
    }
}

// A Helper Object for easy access in your UI components
object SemantixTheme {
    val borders: SemantixBorders
        @Composable get() = LocalSemantixBorders.current
    val spacing: SemantixSpacing
        @Composable get() = LocalSemantixSpacing.current
    val backgrounds: SemantixBackgrounds
        @Composable get() = LocalSemantixBackgrounds.current
}
