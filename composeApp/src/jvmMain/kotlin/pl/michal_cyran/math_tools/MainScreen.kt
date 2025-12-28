package pl.michal_cyran.math_tools

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import mathtools.composeapp.generated.resources.Res
import mathtools.composeapp.generated.resources.content_copy
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import pl.michal_cyran.math_tools.drawings.core.Drawing
import pl.michal_cyran.math_tools.drawings.core.DrawingFactoryImpl
import pl.michal_cyran.math_tools.drawings.core.DrawingParameters
import pl.michal_cyran.math_tools.ui.ParametersSection
import pl.michal_cyran.math_tools.utils.copyImageToClipboard

@Composable
fun MainScreen() {
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    var drawing by remember {
        mutableStateOf(Drawing.COORDINATE_SYSTEM)
    }

    var parameters by remember {
        mutableStateOf<DrawingParameters>(DrawingParameters.GridParameters(2, 2))
    }

    var imageBitmap by remember {
        mutableStateOf(ImageBitmap(1, 1))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        PrimaryTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth()
        ) {
            mainTabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index
                        drawing = tab.drawing
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