package com.rvcoding.propertypricemockup.ui.yourproperties

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rvcoding.propertypricemockup.R
import com.rvcoding.propertypricemockup.domain.Property
import com.rvcoding.propertypricemockup.domain.navigation.Actions
import com.rvcoding.propertypricemockup.ui.component.TopBar
import com.rvcoding.propertypricemockup.ui.theme.BackgroundContainer
import com.rvcoding.propertypricemockup.ui.theme.Tertiary
import org.koin.androidx.compose.koinViewModel


@Composable
fun YourPropertiesScreenRoot(
    vm: YourPropertiesViewModel = koinViewModel(),
) {
    val state by vm.properties.collectAsStateWithLifecycle()

    YourPropertiesScreen(
        state = state,
        onAction = vm::onAction
    )
}

@Composable
fun YourPropertiesScreen(
    state: List<Property>,
    onAction: (Actions.YourProperties) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopBar(TopBar.Title(R.string.your_properties_title))
            if (state.isEmpty()) {
                EmptyState()
            } else {
                PropertiesList(
                    properties = state,
                    onAction = onAction
                )
            }
        }
    }
}

@Composable
fun PropertiesList(
    properties: List<Property>,
    onAction: (Actions.YourProperties) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundContainer)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(176.dp),

            contentPadding = PaddingValues(
                start = 8.dp,
                top = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
        ) {
            items(
                count = properties.size
            ) { index ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(176.dp)
                        .clickable { onAction.invoke(Actions.YourProperties.OnPropertyClick(properties[index].id)) },
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(properties[index].id)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Property ${properties[index].title}",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundContainer),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(200.dp),
                imageVector = ImageVector.vectorResource(R.drawable.glass_of_juice),
                contentDescription = "Empty list"
            )
            Spacer(Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.your_properties_empty_list),
                color = Tertiary,
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun YourPropertiesScreenEmptyPreview() {
    YourPropertiesScreen(
        state = emptyList(),
        onAction = { }
    )
}

@Preview(showBackground = true)
@Composable
fun YourPropertiesScreenNonEmptyPreview() {
    YourPropertiesScreen(
        state = listOf(Property(0, "Property 1")),
        onAction = { }
    )
}