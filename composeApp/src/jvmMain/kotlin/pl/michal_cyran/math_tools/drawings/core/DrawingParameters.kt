package pl.michal_cyran.math_tools.drawings.core

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextMeasurer
import pl.michal_cyran.math_tools.drawings.solids.domain.OtherSolid

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

    sealed class SolidParameters: DrawingParameters() {
        data class PrismParameters(
            val baseSides: Int,
        ) : SolidParameters()

        data class PyramidParameters(
            val baseSides: Int,
        ) : SolidParameters()

        data class OtherSolidParameters(
            val solid: OtherSolid
        ) : SolidParameters()
    }

    data class ClockParameters(
        val hours: Int,
        val minutes: Int,
        val textMeasurer: TextMeasurer,
    ) : DrawingParameters()

    sealed class FunctionParameters(
        val csParameters: CoordinateSystemParameters,
    ): DrawingParameters() {
        class LinearFunctionParameters(
            val a: Float,
            val b: Float,
            csParameters: CoordinateSystemParameters
        ) : FunctionParameters(
            csParameters = csParameters
        )

        class QuadraticFunctionParameters(
            val a: Float,
            val b: Float,
            val c: Float,
            csParameters: CoordinateSystemParameters
        ) : FunctionParameters(
            csParameters = csParameters
        )
    }
}