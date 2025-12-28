package pl.michal_cyran.math_tools.drawings.core

import androidx.compose.ui.graphics.ImageBitmap
import pl.michal_cyran.math_tools.drawings.chessboard.drawChessboardToBitmap
import pl.michal_cyran.math_tools.drawings.clock.drawClockToBitmap
import pl.michal_cyran.math_tools.drawings.coordinate_system.drawCoordinatesSystemToBitmap
import pl.michal_cyran.math_tools.drawings.grid.drawGridToBitmap
import pl.michal_cyran.math_tools.drawings.solids.canvas.drawOtherSolidToBitmap
import pl.michal_cyran.math_tools.drawings.solids.canvas.drawPrismToBitmap
import pl.michal_cyran.math_tools.drawings.solids.canvas.drawPyramidToBitmap

class DrawingFactoryImpl : DrawingFactory {
    override fun createDrawing(parameters: DrawingParameters): ImageBitmap {
        when (parameters) {
            is DrawingParameters.GridParameters -> {
                return drawGridToBitmap(
                    rows = parameters.rows,
                    columns = parameters.columns,
                )
            }
            is DrawingParameters.ChessboardParameters -> {
                return drawChessboardToBitmap(
                    rows = parameters.rows,
                    columns = parameters.columns,
                    firstColor = parameters.firstColor,
                    secondColor = parameters.secondColor,
                )
            }
            is DrawingParameters.CoordinateSystemParameters -> {
                return drawCoordinatesSystemToBitmap(
                    minX = parameters.minX,
                    maxX = parameters.maxX,
                    minY = parameters.minY,
                    maxY = parameters.maxY,
                    includeGrid = parameters.includeGrid,
                    textMeasurer = parameters.textMeasurer,
                )
            }

            is DrawingParameters.SolidParameters -> {
                return when (parameters) {
                    is DrawingParameters.SolidParameters.PrismParameters -> {
                        drawPrismToBitmap(parameters.baseSides)
                    }

                    is DrawingParameters.SolidParameters.PyramidParameters -> {
                        drawPyramidToBitmap(parameters.baseSides)
                    }

                    is DrawingParameters.SolidParameters.OtherSolidParameters -> {
                        drawOtherSolidToBitmap(parameters.solid)
                    }
                }
            }

            is DrawingParameters.ClockParameters -> {
                return drawClockToBitmap(
                    hour = parameters.hours,
                    minute = parameters.minutes,
                    textMeasurer = parameters.textMeasurer,
                )
            }
        }
    }
}