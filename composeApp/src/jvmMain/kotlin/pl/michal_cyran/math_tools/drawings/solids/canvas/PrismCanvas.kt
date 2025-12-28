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

        val sides = getSidesOfPrism(baseSides)

        for (i in 0..sides.lastIndex step 3) {
            val p1 = sides[i] as Pair<Float, Float>
            val p2 = sides[i + 1] as Pair<Float, Float>
            val isDashed = sides[i + 2] as Boolean

            val x1 = p1.first * size.width
            val y1 = p1.second * size.height

            val x2 = p2.first * size.width
            val y2 = p2.second * size.height

            val o1 = Offset(x1, y1)
            val o2 = Offset(x2, y2)

            val effect = if (isDashed) {
                PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
            } else {
                null
            }

            drawLine(color, o1, o2, strokeWidth = 5f, pathEffect = effect)
        }
    }
    return bitmap
}