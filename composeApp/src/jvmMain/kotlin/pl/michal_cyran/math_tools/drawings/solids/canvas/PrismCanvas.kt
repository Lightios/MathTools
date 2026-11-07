package pl.michal_cyran.math_tools.drawings.solids.canvas

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import pl.michal_cyran.math_tools.drawings.core.DefaultParameters

fun drawPrismToBitmap(
    baseSides: Int,
    prismHeight: Int,
    width: Int = DefaultParameters.WIDTH,
    height: Int = DefaultParameters.HEIGHT,
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
        val color = Color.Cyan
        drawRect(color = Color.White.copy(alpha = 0f), topLeft = Offset.Zero, size = size)

        val dashEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)

        for (edge in getSidesOfPrism(baseSides, prismHeight)) {
            val o1 = Offset(edge.p1.first * size.width, edge.p1.second * size.height)
            val o2 = Offset(edge.p2.first * size.width, edge.p2.second * size.height)

            drawLine(
                color = color,
                start = o1,
                end = o2,
                strokeWidth = 5f,
                pathEffect = if (edge.dashed) dashEffect else null,
            )
        }
    }
    return bitmap
}