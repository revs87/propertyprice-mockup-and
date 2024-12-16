package com.rvcoding.propertypricemockup.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rvcoding.propertypricemockup.domain.Property
import com.rvcoding.propertypricemockup.domain.ratingFormatted
import com.rvcoding.propertypricemockup.ui.theme.Background
import com.rvcoding.propertypricemockup.ui.theme.Tertiary
import com.rvcoding.propertypricemockup.ui.theme.TextPrimary
import com.rvcoding.propertypricemockup.ui.theme.TextSecondary


@Composable
fun PropertyCard(
    modifier: Modifier = Modifier,
    property: Property,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .height(176.dp)
            .clickable { onClick.invoke() },
    ) {
        Box(
            modifier = Modifier
                .background(if (property.isFeatured) Tertiary else Background)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = property.name,
                    color = TextPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = property.location,
                    color = TextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = "⭐${property.ratingFormatted()}",
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
                Text(property.lowestPricePerNight.toString(), color = TextSecondary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(4.dp))
                Text(property.lowestPricePerNightCurrency, color = TextSecondary, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}