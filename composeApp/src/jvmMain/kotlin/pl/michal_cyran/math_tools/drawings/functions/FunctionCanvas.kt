package pl.michal_cyran.math_tools.drawings.functions

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import pl.michal_cyran.math_tools.drawings.coordinate_system.drawCoordinatesSystemToBitmap
import pl.michal_cyran.math_tools.drawings.core.DefaultParameters
import pl.michal_cyran.math_tools.drawings.core.DrawingParameters

fun drawFunctionToBitmap(
    parameters: DrawingParameters.FunctionParameters,
): ImageBitmap {
    val width = DefaultParameters.WIDTH
    val height = DefaultParameters.HEIGHT
    val includeGrid = parameters.csParameters.includeGrid
    val textMeasurer = parameters.csParameters.textMeasurer

    val bitmap = drawCoordinatesSystemToBitmap(
        size = parameters.csParameters.size,
        includeGrid = includeGrid,
        textMeasurer = textMeasurer,
    )

    val canvas = Canvas(bitmap)
    val drawScope = CanvasDrawScope()

    val step = 0.1f

    drawScope.draw(
        density = Density(1f),
        layoutDirection = LayoutDirection.Ltr,
        canvas = canvas,
        size = Size(width.toFloat(), height.toFloat()),
    ) {
        val points = mutableListOf<Offset>()

        var x = -parameters.csParameters.size.toFloat()
        while (x < parameters.csParameters.size - 0.1f) {
            val y = findY(parameters, x)

            val offset = coordinatesToOffset(
                x = x,
                y = y,
                size = size,
                range = parameters.csParameters.size,
            )

            points.add(offset)

            if (points.size > 1) {
                drawLine(
                    color = Color.Cyan,
                    start = points[0],
                    end = points[1],
                    strokeWidth = 5f
                )

                points.removeAt(0)
            }

            x += step
        }
    }

    return bitmap
}

fun findY(
    functionParameters: DrawingParameters.FunctionParameters,
    x: Float,
): Float {
    val y = when (functionParameters) {
        is DrawingParameters.FunctionParameters.QuadraticFunctionParameters ->
            functionParameters.a * x * x + functionParameters.b * x + functionParameters.c
        is DrawingParameters.FunctionParameters.LinearFunctionParameters ->
            functionParameters.a * x + functionParameters.b
    }

    return y
}

fun coordinatesToOffset(
    x: Float,
    y: Float,
    size: Size,
    range: Int,
): Offset {
    val percentX = (x + range) * 1f / range
    val percentY = (y + range) * 1f / range

    return Offset(
        x = percentX * size.width,
        y = (1 - percentY) * size.height,
    )
}

@Preview
@Composable
fun FunctionPreview() {
    val textMeasurer = rememberTextMeasurer()

    val bitmap = drawFunctionToBitmap(
        parameters = DrawingParameters.FunctionParameters.QuadraticFunctionParameters(
            a = -1f,
            b = 3f,
            c = 2f,
            csParameters = DrawingParameters.CoordinateSystemParameters(
                size = 5,
                includeGrid = true,
                textMeasurer = textMeasurer
            )
        ),
    )

    Image(
        bitmap = bitmap,
        contentDescription = "Function"
    )
}