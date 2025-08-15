package pl.michal_cyran.math_tools.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPicker(
    title: String,
    initialColor: Color,
    onColorSelected: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    val colorOptions = listOf(
        "Biały" to Color.White,
        "Czarny" to Color.Black,
        "Czerwony" to Color.Red,
        "Zielony" to Color.Green,
        "Niebieski" to Color.Blue,
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedLabel by remember {
        mutableStateOf(colorOptions.find { it.second == initialColor }?.first ?: colorOptions[0].first)
    }

    Column(
        modifier = modifier,
    ) {
        Text(title)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedLabel,
                onValueChange = {},
                readOnly = true,
                label = { Text("Wybierz kolor") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor() // <-- to jest wymagane!
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                colorOptions.forEach { (label, color) ->
                    DropdownMenuItem(
                        text = { Text(label) },
                        onClick = {
                            selectedLabel = label
                            expanded = false
                            onColorSelected(color)
                        }
                    )
                }
            }
        }
    }
}