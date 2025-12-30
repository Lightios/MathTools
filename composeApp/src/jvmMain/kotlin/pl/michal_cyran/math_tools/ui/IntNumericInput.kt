package pl.michal_cyran.math_tools.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NumericInput(
    value: Int,
    onValueChange: (Int) -> Unit,
    label: String,
    minValue: Int,
    maxValue: Int,
    modifier: Modifier = Modifier
) {
    NumericInputBase(
        textValue = value.toString(),
        onTextChange = {
            it.toIntOrNull()?.let { v ->
                onValueChange(v.coerceIn(minValue, maxValue))
            }
        },
        sliderValue = value.toFloat(),
        onSliderChange = {
            onValueChange(it.toInt().coerceIn(minValue, maxValue))
        },
        sliderRange = minValue.toFloat()..maxValue.toFloat(),
        onIncrement = {
            onValueChange((value + 1).coerceAtMost(maxValue))
        },
        onDecrement = {
            onValueChange((value - 1).coerceAtLeast(minValue))
        },
        label = label,
        modifier = modifier
    )
}
