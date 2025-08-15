package pl.michal_cyran.math_tools

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import mathtools.composeapp.generated.resources.Res
import mathtools.composeapp.generated.resources.chart_data
import mathtools.composeapp.generated.resources.chess
import mathtools.composeapp.generated.resources.compose_multiplatform
import mathtools.composeapp.generated.resources.content_copy
import mathtools.composeapp.generated.resources.coordinate_system
import mathtools.composeapp.generated.resources.table
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import pl.michal_cyran.math_tools.drawings.core.Drawing
import pl.michal_cyran.math_tools.drawings.core.DrawingFactoryImpl
import pl.michal_cyran.math_tools.drawings.core.DrawingParameters
import pl.michal_cyran.math_tools.ui.ParametersSection
import pl.michal_cyran.math_tools.utils.copyImageToClipboard

@Composable
fun MainScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }

    var drawing by remember { mutableStateOf(Drawing.GRID) }
    var parameters by remember { mutableStateOf<DrawingParameters>(DrawingParameters.GridParameters(2, 2)) }


    var imageBitmap by remember {
        mutableStateOf(ImageBitmap(1, 1))
    }

    val tabs = listOf(
        TabItem("Tabelka", Res.drawable.table),
        TabItem("Szachownica", Res.drawable.chess),
        TabItem("Układ współrzędnych", Res.drawable.chart_data)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Tab Row
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index; drawing = when (index) {
                        0 -> Drawing.GRID
                        1 -> Drawing.CHESSBOARD
                        else -> Drawing.COORDINATE_SYSTEM
                        }
                    },
                    text = { Text(tab.title) },
                    icon = {
                        Icon(
                            painterResource(tab.icon),
                            contentDescription = tab.title,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ParametersSection(
                drawing = drawing,
                onParametersChange = { newParameters ->
                    parameters = newParameters
                    imageBitmap = DrawingFactoryImpl().createDrawing(newParameters)
                }
            )


            // Preview Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Podgląd obiektu",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                Image(
                    bitmap = imageBitmap,
                    contentDescription = "Siatka",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                }
            }

            // Action Button
            Button(
                onClick = {
                imageBitmap = DrawingFactoryImpl().createDrawing(parameters)
                copyImageToClipboard(imageBitmap.toAwtImage())
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.content_copy),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Kopiuj obiekt do schowka",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

data class TabItem(
    val title: String,
    val icon: DrawableResource
)
