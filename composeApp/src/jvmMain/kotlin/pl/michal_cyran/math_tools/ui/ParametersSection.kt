package pl.michal_cyran.math_tools.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pl.michal_cyran.math_tools.drawings.core.DrawingParameters
import pl.michal_cyran.math_tools.drawings.core.Drawing
import pl.michal_cyran.math_tools.drawings.chessboard.ChessboardParametersSection
import pl.michal_cyran.math_tools.drawings.clock.ClockParametersSection
import pl.michal_cyran.math_tools.drawings.coordinate_system.CoordinateSystemParametersSection
import pl.michal_cyran.math_tools.drawings.functions.FunctionParametersSection
import pl.michal_cyran.math_tools.drawings.grid.GridParametersSection
import pl.michal_cyran.math_tools.drawings.solids.ui.SolidsParametersSection

@Composable
fun ParametersSection(
    drawing: Drawing,
    onParametersChange: (DrawingParameters) -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Parametry obiektu",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            when (drawing) {
                Drawing.GRID -> GridParametersSection(
                    onParametersChange = onParametersChange
                )
                Drawing.CHESSBOARD -> ChessboardParametersSection(
                    onParametersChange = onParametersChange
                )
                Drawing.COORDINATE_SYSTEM -> CoordinateSystemParametersSection(
                    onParametersChange = onParametersChange
                )
                Drawing.SOLID -> SolidsParametersSection(
                    onParametersChange = onParametersChange
                )
                Drawing.CLOCK -> ClockParametersSection(
                    onParametersChange = onParametersChange
                )
                Drawing.FUNCTION -> FunctionParametersSection(
                    onParametersChange = onParametersChange
                )
            }
        }
    }
}

