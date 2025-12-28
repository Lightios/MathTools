package pl.michal_cyran.math_tools.drawings.solids.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mathtools.composeapp.generated.resources.Res
import mathtools.composeapp.generated.resources.brightness
import mathtools.composeapp.generated.resources.cube
import mathtools.composeapp.generated.resources.triangle
import org.jetbrains.compose.resources.painterResource
import pl.michal_cyran.math_tools.drawings.solids.domain.OtherSolid
import pl.michal_cyran.math_tools.drawings.solids.domain.OtherSolidTab

@Composable
fun OtherSolidParameters(
    onParametersChange: (OtherSolid) -> Unit = {},
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    var solid by remember {
        mutableStateOf(OtherSolid.CUBE)
    }

    LaunchedEffect(solid) {
        onParametersChange(solid)
    }

    val solidTabs = listOf(
        OtherSolidTab("Sześcian", solid = OtherSolid.CUBE, Res.drawable.cube),
        OtherSolidTab("Stożek", solid = OtherSolid.CONE, Res.drawable.triangle),
        OtherSolidTab("Walec", solid = OtherSolid.ROLLER, Res.drawable.brightness),
        OtherSolidTab("Kula", solid = OtherSolid.SPHERE, Res.drawable.brightness),
    )
    SecondaryTabRow(
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
}