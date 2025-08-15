package pl.michal_cyran.math_tools.drawings.core

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextMeasurer

sealed class DrawingParameters {
    data class GridParameters(
        val rows: Int,
        val columns: Int,
    ) : DrawingParameters()

    data class ChessboardParameters(
        val rows: Int,
        val columns: Int,
        val firstColor: Color,
        val secondColor: Color,
    ) : DrawingParameters()

    data class CoordinateSystemParameters(
        val minX: Int,
        val maxX: Int,
        val minY: Int,
        val maxY: Int,
        val includeGrid: Boolean = false,
        val textMeasurer: TextMeasurer,
    ) : DrawingParameters()
}