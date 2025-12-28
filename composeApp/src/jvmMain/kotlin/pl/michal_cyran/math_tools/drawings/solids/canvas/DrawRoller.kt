package pl.michal_cyran.math_tools.drawings.solids.canvas

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

fun drawRoller(
    size: Size,
    drawScope: DrawScope,
) {
    val height = size.height / 2
    val color = Color.Cyan

    val centerX = size.width / 2f
    val centerY = size.height / 2f

    val radiusX = size.width / 6f
    val radiusY = radiusX / 2f

    drawScope.drawOval(
        color = Color.Cyan,
        topLeft = Offset(centerX - radiusX, centerY - height / 2),
        size = Size(radiusX * 2, radiusY * 2),
        style = Stroke(width = 5f)
    )

    drawScope.drawOval(
        color = Color.Cyan,
        topLeft = Offset(centerX - radiusX, centerY + height / 2),
        size = Size(radiusX * 2, radiusY * 2),
        style = Stroke(width = 5f)
    )

    val x1 = centerX - radiusX
    val x2 = centerX + radiusX
    val y1 = centerY - height / 2 + radiusY
    val y2 = centerY + height / 2 + radiusY


    drawScope.drawLine(color, Offset(x1, y1), Offset(x1, y2), strokeWidth = 5f)
    drawScope.drawLine(color, Offset(x2, y1), Offset(x2, y2), strokeWidth = 5f)
}

@androidx.compose.desktop.ui.tooling.preview.Preview
@Composable
fun RollerPreview() {
    val width = 1000
    val height = 1000

    val drawScope = CanvasDrawScope()
    val size = Size(width.toFloat(), height.toFloat())
    val bitmap = ImageBitmap(width, height)
    val canvas = Canvas(bitmap)

    drawScope.draw(
        density = Density(1f),
        layoutDirection = LayoutDirection.Ltr,
        canvas = canvas,
        size = size,
    ) {
        drawRoller(
            size = size,
            drawScope = this,
        )
    }

    Image(
        bitmap = bitmap,
        contentDescription = "Roller"
    )
}