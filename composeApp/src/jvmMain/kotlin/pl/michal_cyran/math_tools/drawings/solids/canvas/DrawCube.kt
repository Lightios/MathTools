package pl.michal_cyran.math_tools.drawings.solids.canvas

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope

fun drawCube(
    size: Size,
    drawScope: DrawScope,
) {
    val a = Pair(0.25f, 0.3f)
    val b = Pair(0.65f, 0.3f)
    val c = Pair(0.75f, 0.2f)
    val d = Pair(0.35f, 0.2f)

    val e = Pair(0.25f, 0.7f)
    val f = Pair(0.65f, 0.7f)
    val g = Pair(0.75f, 0.6f)
    val h = Pair(0.35f, 0.6f)

    val sides = listOf(
        a, b, false,
        b, c, false,
        c, d, false,
        d, a, false,

        a, e, false,
        b, f, false,
        c, g, false,
        d, h, true,

        e, f, false,
        f, g, false,
        g, h, true,
        h, e, true,
    )

    val color = Color.Cyan

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

        drawScope.drawLine(color, o1, o2, strokeWidth = 5f, pathEffect = effect)
    }
}