package pl.michal_cyran.math_tools.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlin.math.round



@Composable
fun NumericInput(
    value: Float,
    onValueChange: (Float) -> Unit,
    label: String,
    minValue: Float,
    maxValue: Float,
    modifier: Modifier = Modifier
) {
    NumericInputBase(
        textValue = value.toString(),
        onTextChange = {
            it.toFloatOrNull()?.let { v ->
                onValueChange(v.coerceIn(minValue, maxValue))
            }
        },
        sliderValue = value,
        onSliderChange = {
            onValueChange(
                round(it * 10) / 10f
                    .coerceIn(minValue, maxValue)
            )
        },
        sliderRange = minValue..maxValue,
        onIncrement = {
            onValueChange((value + 1f).coerceAtMost(maxValue))
        },
        onDecrement = {
            onValueChange((value - 1f).coerceAtLeast(minValue))
        },
        label = label,
        modifier = modifier
    )
}
