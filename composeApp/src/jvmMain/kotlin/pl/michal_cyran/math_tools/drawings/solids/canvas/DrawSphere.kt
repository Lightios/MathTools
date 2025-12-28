package pl.michal_cyran.math_tools.drawings.solids.canvas

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.cos
import kotlin.math.sin

fun drawFilledSphere(
    size: Size,
    drawScope: DrawScope,
) {
    val sphereRadius: Float = 300f

    val latSteps: Int = 8
    val lonSteps: Int = 8
    val yaw: Double = 0.6
    val pitch: Double = -0.35

    val cx = size.width / 2f
    val cy = size.height / 2f

    // Light source is slightly offset to top-left
    val lightOffset = Offset(cx - sphereRadius * 0.3f, cy - sphereRadius * 0.3f)

    drawScope.drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(
                Color(0xFFFFFFFF), // bright highlight
                Color(0xFF5555AA), // mid tone
                Color(0xFF111122)  // shadow edge
            ),
            center = lightOffset,
            radius = sphereRadius * 1.2f
        ),
        radius = sphereRadius,
        center = Offset(cx, cy)
    )
}


fun drawContreSphere(
    size: Size,
    drawScope: DrawScope,
) {
    val sphereRadius: Float = 300f
    val latSteps: Int = 12
    val lonSteps: Int = 12
    val yaw: Double = 0.6     // rotation around Y axis
    val pitch: Double = -0.35

    val cx = size.width / 2f
    val cy = size.height / 2f
    val cameraDist = 3 * sphereRadius

    fun rotateAndProject(x: Double, y: Double, z: Double): Offset {
        val cosY = cos(yaw)
        val sinY = sin(yaw)
        val cosX = cos(pitch)
        val sinX = sin(pitch)

        // rotate around X
        val y1 = y * cosX - z * sinX
        val z1 = y * sinX + z * cosX
        // rotate around Y
        val x2 = x * cosY + z1 * sinY
        val z2 = -x * sinY + z1 * cosY

        // perspective projection
        val zc = z2 + cameraDist
        val f = cameraDist / zc

        return Offset(
            (cx + x2 * f).toFloat(),
            (cy - y1 * f).toFloat()
        )
    }

    // silhouette circle
    drawScope.drawCircle(
        color = Color.DarkGray,
        radius = sphereRadius,
        center = Offset(cx, cy),
        style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f)
    )

    // latitude lines
    for (i in 1 until latSteps) {
        val phi = -Math.PI / 2 + (i * Math.PI) / latSteps
        var prev: Offset? = null
        for (j in 0..lonSteps * 2) {
            val theta = (j * Math.PI) / lonSteps
            val x = sphereRadius * cos(phi) * cos(theta)
            val y = sphereRadius * sin(phi)
            val z = sphereRadius * cos(phi) * sin(theta)
            val p = rotateAndProject(x, y, z)
            prev?.let {
                drawScope.drawLine(Color.Gray, it, p, strokeWidth = 1f)
            }
            prev = p
        }
    }

    // longitude lines
    for (j in 0 until lonSteps) {
        val theta = (j * 2 * Math.PI) / lonSteps
        var prev: Offset? = null
        for (i in 0..latSteps) {
            val phi = -Math.PI / 2 + (i * Math.PI) / latSteps
            val x = sphereRadius * cos(phi) * cos(theta)
            val y = sphereRadius * sin(phi)
            val z = sphereRadius * cos(phi) * sin(theta)
            val p = rotateAndProject(x, y, z)
            prev?.let {
                drawScope.drawLine(Color.Gray, it, p, strokeWidth = 1f)
            }
            prev = p
        }
    }
}