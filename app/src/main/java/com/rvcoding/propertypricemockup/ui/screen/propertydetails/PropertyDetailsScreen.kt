package com.rvcoding.propertypricemockup.ui.screen.propertydetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rvcoding.propertypricemockup.domain.Property
import com.rvcoding.propertypricemockup.domain.navigation.Actions
import com.rvcoding.propertypricemockup.ui.theme.Secondary


@Composable
fun PropertyDetailsScreenRoot(
    id: Long,
    vm: PropertyDetailsViewModel = hiltViewModel(),
) {
    LaunchedEffect(id) { vm.setPropertyId(id) }
    val property by vm.property.collectAsStateWithLifecycle()

    PropertyDetailsScreen(
        property = property,
        onAction = vm::onAction
    )
}

@Composable
fun PropertyDetailsScreen(
    property: Property,
    onAction: (Actions.PropertyDetails) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(text = property.name, color = Secondary)
    }
}


@Preview(showBackground = true)
@Composable
fun PropertyDetailsScreenPreview() {
    PropertyDetailsScreen(
        property = Property.INITIAL,
        onAction = {}
    )
}