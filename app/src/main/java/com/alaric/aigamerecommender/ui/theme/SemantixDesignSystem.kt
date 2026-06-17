package com.alaric.aigamerecommender.ui.theme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class SemantixBorders(
    val thickness: Dp = 0.dp,
    val glowRadius: Dp = 0.dp,
    val glowColor: Color = Color.Transparent
)

data class SemantixSpacing(
    val cardPadding: Dp = 16.dp,
    val itemSpacing: Dp = 8.dp
)

data class SemantixBackgrounds(
    val screen: Brush,
    val surface: Brush, // For Cards and Bottom Sheets
    val navBar: Brush
)


val LocalSemantixBorders = staticCompositionLocalOf { SemantixBorders() }
val LocalSemantixSpacing = staticCompositionLocalOf { SemantixSpacing() }

val LocalSemantixBackgrounds = staticCompositionLocalOf {
    SemantixBackgrounds(SolidColor(Color.Transparent), SolidColor(Color.Transparent), SolidColor(Color.Transparent))
}