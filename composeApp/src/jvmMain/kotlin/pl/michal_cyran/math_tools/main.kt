package pl.michal_cyran.math_tools

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import pl.michal_cyran.math_tools.theme.MathToolsTheme
import pl.michal_cyran.website.ui.theme.AppThemeM3

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MathTools",
        state = WindowState(
            width = 700.dp,
            height = 800.dp,
        ),
    ) {
        AppThemeM3(darkTheme = true) {
            MainScreen()
        }
    }
}