package pl.michal_cyran.math_tools

import mathtools.composeapp.generated.resources.Res
import mathtools.composeapp.generated.resources.chart_data
import mathtools.composeapp.generated.resources.chess
import mathtools.composeapp.generated.resources.clock
import mathtools.composeapp.generated.resources.cube
import mathtools.composeapp.generated.resources.table
import org.jetbrains.compose.resources.DrawableResource
import pl.michal_cyran.math_tools.drawings.core.Drawing

data class TabData(
    val name: String,
    val drawing: Drawing,
    val icon: DrawableResource,
)

val mainTabs = listOf(
    TabData("Układ współrzędnych", Drawing.COORDINATE_SYSTEM, Res.drawable.chart_data),
    TabData("Bryły", Drawing.SOLID, Res.drawable.cube),
    TabData("Tabelka", Drawing.GRID, Res.drawable.table),
    TabData("Zegar", Drawing.CLOCK, Res.drawable.clock),
    TabData("Szachownica", Drawing.CHESSBOARD, Res.drawable.chess),
)