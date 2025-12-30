package pl.michal_cyran.math_tools.drawings.solids.ui.composables

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
import pl.michal_cyran.math_tools.ui.NumericInput

@Composable
fun PrismParametersSection(
    onParametersChange: (Int) -> Unit = {},
) {
    var baseSides by remember { mutableStateOf(3) }

    LaunchedEffect(baseSides) {
        onParametersChange(baseSides)
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
    }
}