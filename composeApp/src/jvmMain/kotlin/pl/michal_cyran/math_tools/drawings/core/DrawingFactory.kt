package pl.michal_cyran.math_tools.drawings.core

import androidx.compose.ui.graphics.ImageBitmap

interface DrawingFactory {
    fun createDrawing(
        parameters: DrawingParameters,
    ): ImageBitmap
}