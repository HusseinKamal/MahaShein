package com.hussein.mahashin.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hussein.mahashin.core.data.util.ColorBlack
import com.hussein.mahashin.core.data.util.ColorPrimary
import com.hussein.mahashin.core.data.util.ColorWhite
import com.hussein.mahashin.core.data.util.GrayColor
import com.hussein.mahashin.core.data.util.colorPalette
import com.hussein.mahashin.core.domain.Order
import com.hussein.mahashin.presentation.compose.Tag
import mahashin.composeapp.generated.resources.Res
import mahashin.composeapp.generated.resources.description
import mahashin.composeapp.generated.resources.num_of_products
import mahashin.composeapp.generated.resources.ordered
import mahashin.composeapp.generated.resources.products
import mahashin.composeapp.generated.resources.profit
import mahashin.composeapp.generated.resources.riyal_price
import mahashin.composeapp.generated.resources.shipment_fees
import mahashin.composeapp.generated.resources.total_price
import mahashin.composeapp.generated.resources.total_sell_price
import org.jetbrains.compose.resources.stringResource

@Composable
fun OrderView(
    order: Order,
    onClick: () -> Unit,
    onDelete:(Int) -> Unit
) {
    Card(
        modifier = Modifier // Allows external modifiers to be applied
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        backgroundColor = GrayColor,// Consistent spacing
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp), // Standard padding
            verticalAlignment = Alignment.CenterVertically // Vertically center content
        ) {
            Column(
                modifier = Modifier.weight(1f) // Occupy remaining space
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = order.name,
                        style = MaterialTheme.typography.body1,
                        color = ColorBlack,
                        maxLines = 1, // Prevent text overflow for long item names
                        overflow = TextOverflow.Ellipsis // Show ellipsis if text overflows
                    )

                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically){
                        AnimatedVisibility(order.products.isNotEmpty()) {
                            Tag(
                                text = "${stringResource(Res.string.profit)}: ${
                                    order.products.fold(
                                        0.0
                                    ) { acc, product -> acc + product.priceSell.toDouble() } - order.totalBuyPrice - order.totalShipmentPrice
                                }",
                            )
                        }
                        IconButton(
                            onClick = { onDelete(order.id) },
                            modifier = Modifier
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colors.onSurface // Make icon visible on different backgrounds
                            )
                        }
                    }

                }
                Spacer(modifier = Modifier.height(4.dp)) // Small spacer between title and details
                Text(
                    text = "${stringResource(Res.string.description)}: ${order.description}",
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis, // Show ellipsis if text overflows
                    color = ColorBlack
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${stringResource(Res.string.total_price)}: ${order.products.fold(0.0) { acc, product -> acc + product.priceBuy.toDouble() }}",
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis, // Show ellipsis if text overflows
                    color = ColorBlack
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${stringResource(Res.string.total_sell_price)}: ${
                        order.products.fold(
                            0.0
                        ) { acc, product -> acc + product.priceSell.toDouble() }
                    }",
                    //text = "${stringResource(Res.string.total_sell_price)}: ${order.totalSellPrice}",
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis, // Show ellipsis if text overflows
                    color = ColorBlack
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${stringResource(Res.string.shipment_fees)}: ${order.totalShipmentPrice}",
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis, // Show ellipsis if text overflows
                    color = ColorBlack
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    buildAnnotatedString {
                        append("${stringResource(Res.string.num_of_products)}: ")
                        append(order.products.size.toString())
                        append(" ")
                        append(stringResource(Res.string.products))
                    },
                    fontSize = 12.sp,
                    color = ColorBlack,
                    style = LocalTextStyle.current.copy(
                        textDirection = TextDirection.Rtl // Set to RTL
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${stringResource(Res.string.shipment_fees)}: ${order.totalShipmentPrice}",
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis, // Show ellipsis if text overflows
                    color = ColorBlack
                )
                Spacer(modifier = Modifier.width(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,// Space elements evenly
                ) {
                    Text(
                        text = "${stringResource(Res.string.riyal_price)}: ${order.riyalSaudiPrice}",
                        fontSize = 12.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis, // Show ellipsis if text overflows
                        color = ColorBlack
                    )

                    Surface(
                        color = ColorPrimary, // Yellow background color
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .border(1.dp, ColorBlack, RoundedCornerShape(16.dp))
                    ) {

                        Text(
                            text = "${stringResource(Res.string.ordered)}: ${order.date}",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(4.dp),
                            color = ColorWhite
                        )
                    }
                }
            }
        }
    }
}