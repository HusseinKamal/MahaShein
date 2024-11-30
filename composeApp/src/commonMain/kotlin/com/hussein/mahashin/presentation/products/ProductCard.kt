package com.hussein.mahashin.presentation.products

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hussein.mahashin.core.data.util.ColorPrimary
import com.hussein.mahashin.core.data.util.ColorWhite
import com.hussein.mahashin.core.data.util.generateRandomColor
import com.hussein.mahashin.core.domain.Product
import com.hussein.mahashin.presentation.compose.Tag
import mahashin.composeapp.generated.resources.Res
import mahashin.composeapp.generated.resources.egp
import mahashin.composeapp.generated.resources.prepayment
import mahashin.composeapp.generated.resources.remaining_payment
import mahashin.composeapp.generated.resources.total_price
import mahashin.composeapp.generated.resources.total_sell_price
import mahashin.composeapp.generated.resources.username
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductCard(cardData: Product,
                onDelete: (Int) -> Unit,
                onClick: () -> Unit) {


    //var cardHeight by remember { mutableStateOf(0.dp) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        /*.onGloballyPositioned { coordinates ->
            cardHeight = with(LocalDensity.current) { coordinates.size.height.toDp() }
        }*/
        backgroundColor = ColorPrimary,
        elevation = 8.dp
    ) {

        Box(modifier = Modifier.fillMaxWidth()) { // Box to position delete icon


            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()

            ) {

                Text(
                    text = cardData.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = ColorWhite
                )

                Spacer( modifier = Modifier.height(8.dp))

                Text(
                    text = cardData.description,
                    fontSize = 16.sp,
                    color = ColorWhite
                )
                Spacer( modifier = Modifier.height(8.dp))
                Text(
                    text = "${stringResource(Res.string.total_sell_price)} : ${cardData.priceSell} ${
                        stringResource(
                            Res.string.egp)
                    }",
                    fontSize = 16.sp,
                    color = ColorWhite
                )

                Spacer( modifier = Modifier.height(8.dp))
                Text(
                    text = "${stringResource(Res.string.total_price)} : ${cardData.priceBuy} ${
                        stringResource(
                            Res.string.egp)
                    }",
                    fontSize = 16.sp,
                    color = ColorWhite
                )
                Spacer( modifier = Modifier.height(8.dp))
                Text(
                    text = "${stringResource(Res.string.prepayment)} : ${cardData.prepayment} ${
                        stringResource(
                            Res.string.egp)
                    }",
                    fontSize = 16.sp,
                    color = ColorWhite
                )
                AnimatedVisibility(cardData.prepayment.isNotEmpty() && cardData.prepayment.toDouble()!=0.0) {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${stringResource(Res.string.remaining_payment)} : ${cardData.priceSell.toDouble() - cardData.prepayment.toDouble()} ${
                                stringResource(
                                    Res.string.egp
                                )
                            }",
                            fontSize = 16.sp,
                            color = ColorWhite
                        )
                    }

                }
                Spacer( modifier = Modifier.height(8.dp))
                Tag(
                    text = "${stringResource(Res.string.username)} : ${cardData.username}",
                )
            }

            IconButton(
                onClick = { onDelete(cardData.id) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colors.onSurface // Make icon visible on different backgrounds
                )
            }

        }
    }
}