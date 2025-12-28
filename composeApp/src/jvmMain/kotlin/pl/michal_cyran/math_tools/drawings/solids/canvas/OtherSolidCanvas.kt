package pl.michal_cyran.math_tools.drawings.solids.canvas

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import pl.michal_cyran.math_tools.drawings.core.DefaultParameters
import pl.michal_cyran.math_tools.drawings.solids.domain.OtherSolid

fun drawOtherSolidToBitmap(
    solid: OtherSolid,
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
        drawRect(color = Color.White.copy(alpha = 0f), topLeft = Offset.Zero, size = size)

        when (solid) {
            OtherSolid.CUBE -> drawCube(
                size = size,
                drawScope = this,
            )
            OtherSolid.SPHERE -> drawContreSphere(
                size = size,
                drawScope = this,
            )
            OtherSolid.ROLLER -> drawRoller(
                size = size,
                drawScope = this,
            )
            OtherSolid.CONE -> drawCone(
                size = size,
                drawScope = this,
            )
        }
    }
    return bitmap
}