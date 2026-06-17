package com.alaric.aigamerecommender.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

// 1. The Cyberpunk Look (Aggressive, angled cuts)
val CyberpunkShapes = Shapes(
    small = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp), // Buttons
    medium = CutCornerShape(topEnd = 16.dp, bottomStart = 16.dp), // Game Cards
    large = CutCornerShape(24.dp)
)

// 2. The Modern Look (Soft, bubbly, Apple-esque)
val ModernShapes = Shapes(
    small = RoundedCornerShape(50), // Fully pill-shaped buttons
    medium = RoundedCornerShape(24.dp), // Very round cards
    large = RoundedCornerShape(32.dp)
)

// 3. The Retro CRT Look (Brutalist, pure 90s squares)
val RetroShapes = Shapes(
    small = RectangleShape as CornerBasedShape,
    medium = RectangleShape as CornerBasedShape,
    large = RectangleShape as CornerBasedShape
)