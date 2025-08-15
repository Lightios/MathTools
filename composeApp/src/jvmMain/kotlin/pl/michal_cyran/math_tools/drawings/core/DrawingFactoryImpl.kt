package pl.michal_cyran.math_tools.drawings.core

import androidx.compose.ui.graphics.ImageBitmap
import pl.michal_cyran.math_tools.drawings.chessboard.drawChessboardToBitmap
import pl.michal_cyran.math_tools.drawings.coordinate_system.drawCoordinatesSystemToBitmap
import pl.michal_cyran.math_tools.drawings.grid.drawGridToBitmap

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
        }
    }
}