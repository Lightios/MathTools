package pl.michal_cyran.math_tools.drawings.solids.ui

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
import org.jetbrains.compose.resources.painterResource
import pl.michal_cyran.math_tools.drawings.core.DrawingParameters
import pl.michal_cyran.math_tools.drawings.solids.domain.MainSolid
import pl.michal_cyran.math_tools.drawings.solids.domain.MainSolidTab

@Composable
fun SolidsParametersSection(
    onParametersChange: (DrawingParameters.SolidParameters) -> Unit = {}
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    var solid by remember {
        mutableStateOf(MainSolid.PRISM)
    }

    val solidTabs = listOf(
        MainSolidTab("Graniastosłup", solid = MainSolid.PRISM, Res.drawable.rectangle),
        MainSolidTab("Ostrosłup", solid = MainSolid.PYRAMID, Res.drawable.triangle),
        MainSolidTab("Inne", solid = MainSolid.OTHER, Res.drawable.more),
    )

    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.fillMaxWidth()
    ) {
        solidTabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    solid = tab.solid
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

    SolidsParametersSubsection(
        solid = solid,
        onParametersChange = onParametersChange
    )
}


