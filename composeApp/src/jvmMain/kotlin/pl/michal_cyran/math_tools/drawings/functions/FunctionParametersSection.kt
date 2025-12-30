package pl.michal_cyran.math_tools.drawings.functions

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mathtools.composeapp.generated.resources.Res
import mathtools.composeapp.generated.resources.more
import mathtools.composeapp.generated.resources.rectangle
import mathtools.composeapp.generated.resources.triangle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import pl.michal_cyran.math_tools.drawings.core.DrawingParameters
import pl.michal_cyran.math_tools.drawings.functions.domain.MathFunction
import pl.michal_cyran.math_tools.drawings.functions.ui.LinearFunctionParametersSection
import pl.michal_cyran.math_tools.drawings.functions.ui.QuadraticFunctionParametersSection
import pl.michal_cyran.math_tools.drawings.solids.domain.MainSolid
import pl.michal_cyran.math_tools.drawings.solids.domain.MainSolidTab

@Composable
fun FunctionParametersSection(
    onParametersChange: (DrawingParameters.FunctionParameters) -> Unit = {},
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    var function by remember {
        mutableStateOf(
            MathFunction.LINEAR
        )
    }

    val functionTabs = listOf(
        FunctionTab("Liniowa", function = MathFunction.LINEAR, Res.drawable.rectangle),
        FunctionTab("Kwadratowa", function = MathFunction.QUADRATIC, Res.drawable.triangle),
    )

    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.fillMaxWidth()
    ) {
        functionTabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    function = tab.function
                },
                text = { Text(tab.name) },
                icon = {
                    Icon(
                        painterResource(tab.icon),
                        contentDescription = tab.name,
                        modifier = Modifier.size(24.dp)
                    )
                },
            )
        }
    }

    when (function) {
        MathFunction.LINEAR -> LinearFunctionParametersSection(
            onParametersChange = onParametersChange
        )
        MathFunction.QUADRATIC -> QuadraticFunctionParametersSection(
            onParametersChange = onParametersChange
        )
    }
}

data class FunctionTab(
    val name: String,
    val function: MathFunction,
    val icon: DrawableResource,
)