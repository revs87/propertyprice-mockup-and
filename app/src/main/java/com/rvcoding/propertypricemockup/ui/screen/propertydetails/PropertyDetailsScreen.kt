package com.rvcoding.propertypricemockup.ui.screen.propertydetails

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.rvcoding.propertypricemockup.R
import com.rvcoding.propertypricemockup.domain.Property
import com.rvcoding.propertypricemockup.domain.navigation.Actions
import com.rvcoding.propertypricemockup.ui.component.PropertyCard
import com.rvcoding.propertypricemockup.ui.component.TopBar
import com.rvcoding.propertypricemockup.ui.screen.yourproperties.LoadingState
import com.rvcoding.propertypricemockup.ui.theme.Background
import com.rvcoding.propertypricemockup.ui.theme.BackgroundContainer
import com.rvcoding.propertypricemockup.ui.theme.Secondary


@Composable
fun PropertyDetailsScreenRoot(
    id: Long,
    vm: PropertyDetailsViewModel = hiltViewModel(),
) {
    LaunchedEffect(id) { vm.setPropertyId(id) }
    val property by vm.property.collectAsStateWithLifecycle()
    val extraCurrencies by vm.extraCurrencies.collectAsStateWithLifecycle()
    val isLoading by vm.isLoading.collectAsStateWithLifecycle()

    BackHandler {
        vm.onAction(Actions.PropertyDetails.NavigateBack)
    }

    PropertyDetailsScreen(
        property = property,
        isLoading = isLoading,
        extraCurrencies = extraCurrencies,
        onAction = vm::onAction
    )
}

@Composable
fun PropertyDetailsScreen(
    property: Property,
    isLoading: Boolean,
    extraCurrencies: Map<String, String>,
    onAction: (Actions.PropertyDetails) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().background(Background)
    ) {
        if (isLoading) {
            LoadingState()
        } else {
            Box(
                modifier = Modifier.fillMaxSize().background(BackgroundContainer),
                contentAlignment = Alignment.TopStart
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TopBar(
                        item = TopBar.BackNavigatorWithTitle(
                            titleRes = R.string.your_property_details_title,
                            onLeftButtonClicked = { onAction.invoke(Actions.PropertyDetails.NavigateBack) }
                        )
                    )
                    AsyncImage(
                        model = property.imgUrl,
                        contentDescription = property.name,
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                    PropertyCard(
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        property = property,
                        onClick = { }
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
                        text = property.overview,
                        color = Secondary,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Justify,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Featured: ${property.isFeatured}",
                        color = Secondary,
                        fontSize = 14.sp
                    )
                    extraCurrencies.keys.forEach { currency ->
                        Text(
                            text = "$currency: ${extraCurrencies[currency]}",
                            color = Secondary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PropertyDetailsScreenPreview() {
    PropertyDetailsScreen(
        property = Property.INITIAL,
        isLoading = false,
        extraCurrencies = mapOf("GBP" to "1.2"),
        onAction = {}
    )
}