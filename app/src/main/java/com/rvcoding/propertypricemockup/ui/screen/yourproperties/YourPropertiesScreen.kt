package com.rvcoding.propertypricemockup.ui.screen.yourproperties

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rvcoding.propertypricemockup.R
import com.rvcoding.propertypricemockup.domain.Property
import com.rvcoding.propertypricemockup.domain.ratingFormatted
import com.rvcoding.propertypricemockup.ui.component.TopBar
import com.rvcoding.propertypricemockup.ui.navigation.Actions
import com.rvcoding.propertypricemockup.ui.theme.Background
import com.rvcoding.propertypricemockup.ui.theme.BackgroundContainer
import com.rvcoding.propertypricemockup.ui.theme.Primary
import com.rvcoding.propertypricemockup.ui.theme.Secondary
import com.rvcoding.propertypricemockup.ui.theme.Tertiary
import com.rvcoding.propertypricemockup.ui.theme.TextPrimary
import com.rvcoding.propertypricemockup.ui.theme.TextSecondary


/**
 * Good practice - declare a Root composable and a Screen composable:
 * - Initialize the viewModel in the Root composable
 * - The Screen composable is now preview-able
 * */
@Composable
fun YourPropertiesScreenRoot(
    vm: YourPropertiesViewModel = hiltViewModel(),
) {
    val properties by vm.properties.collectAsStateWithLifecycle()
    val isLoading by vm.isLoading.collectAsStateWithLifecycle()
    val canScrollToTop by vm.canScrollToTop.collectAsStateWithLifecycle()

    YourPropertiesScreen(
        properties = properties,
        isLoading = isLoading,
        canScrollToTop = canScrollToTop,
        lazyGridState = vm.lazyGridState,
        onAction = vm::onAction
    )
}

@Composable
fun YourPropertiesScreen(
    properties: List<Property>,
    isLoading: Boolean,
    canScrollToTop: Boolean,
    lazyGridState: LazyGridState,
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
            when {
                isLoading -> LoadingState()
                properties.isEmpty() -> EmptyState()
                else -> PropertiesList(
                    properties = properties,
                    canScrollToTop = canScrollToTop,
                    lazyGridState = lazyGridState,
                    onAction = onAction
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertiesList(
    properties: List<Property>,
    canScrollToTop: Boolean,
    lazyGridState: LazyGridState,
    onAction: (Actions.YourProperties) -> Unit
) {
    var refreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundContainer),
        isRefreshing = refreshing,
        state = pullRefreshState,
        onRefresh = {
            refreshing = !refreshing
            onAction.invoke(Actions.YourProperties.Refresh)
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(176.dp),
            state = lazyGridState,
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
                        .clickable {
                            onAction.invoke(
                                Actions.YourProperties.OnPropertyClick(
                                    properties[index].id
                                )
                            )
                        },
                ) {
                    Box(
                        modifier = Modifier
                            .background(Background)
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = properties[index].name,
                            color = TextPrimary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            Text(
                                text = "⭐${properties[index].ratingFormatted()}",
                                color = TextSecondary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(properties[index].lowestPricePerNight.toString(), color = TextSecondary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.width(4.dp))
                            Text(properties[index].lowestPricePerNightCurrency, color = TextSecondary, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
        if (canScrollToTop) {
            ScrollToTopButton(onAction)
        }
    }
}

@Composable
fun ScrollToTopButton(onAction: (Actions.YourProperties) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FilledIconButton(
            onClick = { onAction.invoke(Actions.YourProperties.GoToTop) },
            modifier = Modifier
                .padding(end = 16.dp, bottom = 32.dp)
                .size(65.dp),
            shape = RoundedCornerShape(12.dp),
            colors = IconButtonColors(
                containerColor = Primary,
                contentColor = Primary,
                disabledContainerColor = Tertiary,
                disabledContentColor = Tertiary
            )
        ) {
            Icon(
                modifier = Modifier.size(36.dp),
                imageVector = Icons.Filled.KeyboardArrowUp,
                contentDescription = "Add new meme",
                tint = Secondary
            )
        }
    }
}

/**
 * Stateless composable.
 * */
@Composable
fun EmptyState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundContainer),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundContainer),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun YourPropertiesScreenLoadingPreview() {
    YourPropertiesScreen(
        properties = emptyList(),
        isLoading = true,
        canScrollToTop = false,
        onAction = { },
        lazyGridState = LazyGridState()
    )
}

@Preview(showBackground = true)
@Composable
fun YourPropertiesScreenEmptyPreview() {
    YourPropertiesScreen(
        properties = emptyList(),
        isLoading = false,
        canScrollToTop = false,
        onAction = { },
        lazyGridState = LazyGridState()
    )
}

@Preview(showBackground = true)
@Composable
fun YourPropertiesScreenNonEmptyPreview() {
    YourPropertiesScreen(
        properties = listOf(Property(
            id = 0,
            name = "Property 1",
            isFeatured = false,
            rating = 4.5,
            lowestPricePerNight = 100.0,
            lowestPricePerNightCurrency = "EUR"
        )),
        isLoading = false,
        canScrollToTop = false,
        onAction = { },
        lazyGridState = LazyGridState()
    )
}