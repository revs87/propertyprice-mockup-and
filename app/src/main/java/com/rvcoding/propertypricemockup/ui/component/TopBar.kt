package com.rvcoding.propertypricemockup.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rvcoding.propertypricemockup.R
import com.rvcoding.propertypricemockup.ui.theme.Background
import com.rvcoding.propertypricemockup.ui.theme.Secondary
import com.rvcoding.propertypricemockup.ui.theme.TextPrimary
import com.rvcoding.propertypricemockup.ui.theme.TextSecondary


@Composable
fun TopBar(item: TopBar) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .padding(top = 32.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            .height(32.dp)
    ) {
        when (item) {
            is TopBar.Title -> {
                Text(
                    text = stringResource(item.titleRes),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Companion.Medium,
                    color = item.titleTint,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Companion.Center,
                            trim = LineHeightStyle.Trim.Companion.Both
                        )
                    )
                )
            }

            is TopBar.BackNavigatorWithTitle -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = CenterVertically
                ) {
                    FilledIconButton(
                        onClick = { item.onLeftButtonClicked.invoke() },
                        modifier = Modifier.padding(end = 16.dp).size(32.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = IconButtonColors(
                            containerColor = item.leftButtonColor,
                            contentColor = item.leftButtonColor,
                            disabledContainerColor = Secondary,
                            disabledContentColor = Secondary
                        )
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = item.leftButtonIconRes,
                            contentDescription = "Navigate back",
                            tint = item.leftButtonIconTint
                        )
                    }

                    Text(
                        text = stringResource(item.titleRes),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Companion.Medium,
                        color = item.titleTint,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            ),
                            lineHeightStyle = LineHeightStyle(
                                alignment = LineHeightStyle.Alignment.Companion.Center,
                                trim = LineHeightStyle.Trim.Companion.Both
                            )
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarTitlePreview() {
    TopBar(
        item = TopBar.Title(
            titleRes = R.string.app_name
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarBackNavigatorWithTitlePreview() {
    TopBar(
        item = TopBar.BackNavigatorWithTitle(
            titleRes = R.string.app_name,
            onLeftButtonClicked = {}
        )
    )
}


sealed interface TopBar {
    data class Title(
        val titleRes: Int,
        val titleTint: Color = TextPrimary
    ) : TopBar

    data class BackNavigatorWithTitle(
        val titleRes: Int,
        val titleTint: Color = TextPrimary,
        val onLeftButtonClicked: () -> Unit,
        val leftButtonIconRes: ImageVector = Icons.AutoMirrored.Rounded.ArrowBack,
        val leftButtonIconTint: Color = TextSecondary,
        val leftButtonColor: Color = Color.Transparent,
    ) : TopBar
}