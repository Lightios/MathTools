package pl.michal_cyran.math_tools.drawings.clock

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import pl.michal_cyran.math_tools.drawings.core.DefaultParameters
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun drawClockToBitmap(
    hour: Int,
    minute: Int,
    width: Int = DefaultParameters.WIDTH,
    height: Int = DefaultParameters.HEIGHT,
    textMeasurer: TextMeasurer,
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
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 2f

        drawCircle(
            color = Color.Gray,
            radius = radius,
            center = center,
            style = Stroke(width = 2f)
        )

        for (h in 1..12) {
            val angle = (h / 12.0) * 2 * PI - PI / 2
            val startRadius = radius * 0.9f
            val endRadius = radius
            val start = Offset(
                (center.x + startRadius * cos(angle)).toFloat(),
                (center.y + startRadius * sin(angle)).toFloat()
            )
            val end = Offset(
                (center.x + endRadius * cos(angle)).toFloat(),
                (center.y + endRadius * sin(angle)).toFloat()
            )
            drawLine(color = Color.Gray, start = start, end = end, strokeWidth = 4f)
        }

        val hourAngle = ((hour % 12 + minute / 60.0) / 12.0) * 2 * PI - PI / 2
        val hourHandLength = radius * 0.5f
        val hourHandEnd = Offset(
            (center.x + hourHandLength * cos(hourAngle)).toFloat(),
            (center.y + hourHandLength * sin(hourAngle)).toFloat()
        )
        drawLine(color = Color.Gray, start = center, end = hourHandEnd, strokeWidth = 8f)

        val minuteAngle = (minute / 60.0) * 2 * PI - PI / 2
        val minuteHandLength = radius * 0.7f
        val minuteHandEnd = Offset(
            (center.x + minuteHandLength * cos(minuteAngle)).toFloat(),
            (center.y + minuteHandLength * sin(minuteAngle)).toFloat()
        )
        drawLine(color = Color.Gray, start = center, end = minuteHandEnd, strokeWidth = 4f)

        for (i in 1..12) {
            val angle = (i / 12.0) * 2 * PI - PI / 2
            val distance = radius * 0.85f
            val position = Offset(
                (center.x + distance * cos(angle)).toFloat(),
                (center.y + distance * sin(angle)).toFloat()
            )
            val (width, height) = textMeasurer.measure(i.toString()).size
            drawText(
                textMeasurer = textMeasurer,
                text = i.toString(),
                topLeft = Offset(position.x - width, position.y - height),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = Bold
                )
            )
        }
    }
    return bitmap
}
