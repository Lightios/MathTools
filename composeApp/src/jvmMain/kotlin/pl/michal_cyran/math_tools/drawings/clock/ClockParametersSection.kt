package pl.michal_cyran.math_tools.drawings.clock

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
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import pl.michal_cyran.math_tools.drawings.core.DrawingParameters
import pl.michal_cyran.math_tools.ui.NumericInput
import kotlin.math.min

@Composable
fun ClockParametersSection(
    onParametersChange: (DrawingParameters.ClockParameters) -> Unit = {}
) {
    var hours by remember { mutableStateOf(2) }
    var minutes by remember { mutableStateOf(2) }
    val textMeasurer = rememberTextMeasurer()

    LaunchedEffect(hours, minutes) {
        onParametersChange(DrawingParameters.ClockParameters(hours, minutes, textMeasurer))
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        NumericInput(
            value = hours,
            onValueChange = {
                hours = it
            },
            label = "Godziny",
            minValue = 0,
            maxValue = 12,
            modifier = Modifier.weight(1f),
        )

        NumericInput(
            value = minutes,
            onValueChange = {
                minutes = it
            },
            label = "Minuty",
            minValue = 0,
            maxValue = 60,
            modifier = Modifier.weight(1f),
        )
    }
}