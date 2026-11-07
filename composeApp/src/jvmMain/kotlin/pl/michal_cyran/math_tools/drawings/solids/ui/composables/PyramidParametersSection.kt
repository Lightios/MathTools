package pl.michal_cyran.math_tools.drawings.solids.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.michal_cyran.math_tools.ui.NumericInput

@Composable
fun PyramidParametersSection(
    onParametersChange: (Int, Int) -> Unit = {_, _ -> },
) {
    var baseSides by remember { mutableStateOf(3) }
    var pyramidHeight by remember { mutableStateOf(5) }

    LaunchedEffect(baseSides, pyramidHeight) {
        onParametersChange(baseSides, pyramidHeight)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        NumericInput(
            value = baseSides,
            onValueChange = {
                baseSides = it
            },
            label = "Liczba boków w podstawie",
            minValue = 3,
            maxValue = 6,
            modifier = Modifier.weight(1f),
        )

        NumericInput(
            value = pyramidHeight,
            onValueChange = {
                pyramidHeight = it
            },
            label = "Wysokość ostrosłupa",
            minValue = 2,
            maxValue = 10,
            modifier = Modifier.weight(1f),
        )
    }
}
