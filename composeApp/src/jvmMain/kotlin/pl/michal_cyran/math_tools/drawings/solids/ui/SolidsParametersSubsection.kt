package pl.michal_cyran.math_tools.drawings.solids.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import pl.michal_cyran.math_tools.drawings.core.DrawingParameters
import pl.michal_cyran.math_tools.drawings.solids.domain.MainSolid
import pl.michal_cyran.math_tools.drawings.solids.ui.composables.PrismParametersSection
import pl.michal_cyran.math_tools.drawings.solids.ui.composables.PyramidParametersSection

@Composable
fun SolidsParametersSubsection(
    solid: MainSolid,
    onParametersChange: (DrawingParameters.SolidParameters) -> Unit = {}
) {

    when (solid) {
        MainSolid.PRISM -> PrismParametersSection(
            onParametersChange = { newBaseSides ->
                onParametersChange(DrawingParameters.SolidParameters.PrismParameters(newBaseSides))
            }
        )
        MainSolid.PYRAMID -> PyramidParametersSection(
            onParametersChange = { newBaseSides ->
                onParametersChange(DrawingParameters.SolidParameters.PyramidParameters(newBaseSides))
            }
        )
        MainSolid.OTHER -> OtherSolidParameters(
            onParametersChange = { solid ->
                onParametersChange(DrawingParameters.SolidParameters.OtherSolidParameters(solid))
            }
        )
    }
}