package pl.michal_cyran.math_tools.drawings.coordinate_system

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import pl.michal_cyran.math_tools.drawings.core.DefaultParameters
import pl.michal_cyran.math_tools.theme.Colors

fun drawCoordinatesSystemToBitmap(
    minX: Int = -10,
    maxX: Int = 10,
    minY: Int = -10,
    maxY: Int = 10,
    includeGrid: Boolean = true,
    width: Int = DefaultParameters.WIDTH,
    height: Int = DefaultParameters.HEIGHT,
    textMeasurer: TextMeasurer,
): ImageBitmap {
    val drawScope = CanvasDrawScope()
    val size = Size(width.toFloat(), height.toFloat())
    val bitmap = ImageBitmap(width, height)
    val canvas = Canvas(bitmap)

    val xLinesCount = maxX - minX
    val yLinesCount = maxY - minY

    drawScope.draw(
        density = Density(1f),
        layoutDirection = LayoutDirection.Ltr,
        canvas = canvas,
        size = size,
    ) {
        for (i in 0..yLinesCount) {
            val yPosition = size.height / yLinesCount * i
            drawLine(
                color = Colors.gray,
                start = Offset(0f, yPosition),
                end = Offset(size.width, yPosition),
                strokeWidth = 2f
            )
        }

        for (i in 0..xLinesCount) {
            val xPosition = size.width / xLinesCount * i
            drawLine(
                color = Colors.gray,
                start = Offset(xPosition, 0f),
                end = Offset(xPosition, size.height),
                strokeWidth = 2f
            )
        }


        drawLine(
            color = Colors.gray,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = 5f
        )

        drawLine(
            color = Colors.gray,
            start = Offset(size.width / 2, 0f),
            end = Offset(size.width / 2, size.height),
            strokeWidth = 5f
        )


        for (i in minX..maxX) {
            val xPosition = size.width / (maxX - minX) * (i - minX) - 5f
            drawText(
                textMeasurer = textMeasurer,
                text = i.toString(),
                topLeft = Offset(xPosition, size.height / 2 + 10f),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = Bold
                )
            )
        }


        for (i in minY..maxY) {
            val yPosition = (size.height / (maxY - minY) * (i - minY) + 5f).coerceIn(0f, size.height)
            drawText(
                textMeasurer = textMeasurer,
                text = (i * -1).toString(),
                topLeft = Offset(size.width / 2 - 20f, yPosition),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = Bold
                )
            )
        }
    }

    return bitmap
}