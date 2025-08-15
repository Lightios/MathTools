package pl.michal_cyran.math_tools.drawings.grid

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

fun drawGridToBitmap(
    rows: Int,
    columns: Int,
    width: Int = 1000,
    height: Int = 1000,
): ImageBitmap {
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
        drawRect(color = Color.White.copy(alpha = 0.01f), topLeft = Offset.Zero, size = size)
        val rowHeight = size.height / rows
        val colWidth = size.width / columns

        for (i in 0..rows) {
            val y = i * rowHeight
            drawLine(
                color = Color.Gray,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 5f
            )
        }

        for (j in 0..columns) {
            val x = j * colWidth
            drawLine(
                color = Color.Gray,
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = 5f
            )
        }
    }
    return bitmap
}