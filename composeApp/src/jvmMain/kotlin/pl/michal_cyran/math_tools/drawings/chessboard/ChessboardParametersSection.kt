package pl.michal_cyran.math_tools.drawings.chessboard

import androidx.compose.ui.graphics.Color
import pl.michal_cyran.math_tools.utils.ColorPicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import pl.michal_cyran.math_tools.drawings.core.DrawingParameters
import pl.michal_cyran.math_tools.ui.NumericInput

@Composable
fun ChessboardParametersSection(
    onParametersChange: (DrawingParameters.ChessboardParameters) -> Unit = {}
) {
    var rows by remember { mutableStateOf(2) }
    var columns by remember { mutableStateOf(2) }
    var firstColor by remember { mutableStateOf(Color.White) }
    var secondColor by remember { mutableStateOf(Color.Black) }

    LaunchedEffect(rows, columns, firstColor, secondColor) {
        onParametersChange(
            DrawingParameters.ChessboardParameters(
                rows = rows,
                columns = columns,
                firstColor = firstColor,
                secondColor = secondColor
            )
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        NumericInput(
            value = rows,
            onValueChange = {
                rows = it
            },
            label = "Wiersze",
            minValue = 1,
            maxValue = 10,
            modifier = Modifier.weight(1f),
        )

        NumericInput(
            value = columns,
            onValueChange = {
                columns = it
            },
            label = "Kolumny",
            minValue = 1,
            maxValue = 10,
            modifier = Modifier.weight(1f),
        )

        ColorPicker(
            "Pierwszy kolor",
            initialColor = firstColor,
            onColorSelected = { newColor ->
                firstColor = newColor
            },
            modifier = Modifier.weight(1f),
        )

        ColorPicker(
            "Drugi kolor",
            initialColor = secondColor,
            onColorSelected = { newColor ->
                secondColor = newColor
            },
            modifier = Modifier.weight(1f),
        )
    }
}