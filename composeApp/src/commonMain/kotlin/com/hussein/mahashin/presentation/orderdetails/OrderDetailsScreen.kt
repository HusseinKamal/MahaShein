package com.hussein.mahashin.presentation.orderdetails
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hussein.mahashin.core.data.util.ColorBlack
import com.hussein.mahashin.core.data.util.ColorPrimary
import com.hussein.mahashin.core.data.util.ColorWhite
import com.hussein.mahashin.core.data.util.OFFWhite
import com.hussein.mahashin.core.domain.Product
import com.hussein.mahashin.presentation.compose.ConfirmationDialog
import com.hussein.mahashin.presentation.compose.ErrorView
import com.hussein.mahashin.presentation.products.ProductCard
import kotlinx.coroutines.launch
import mahashin.composeapp.generated.resources.Res
import mahashin.composeapp.generated.resources.create_order
import mahashin.composeapp.generated.resources.delete_product_msg
import mahashin.composeapp.generated.resources.delete_product_title
import mahashin.composeapp.generated.resources.description
import mahashin.composeapp.generated.resources.name
import mahashin.composeapp.generated.resources.no_products
import mahashin.composeapp.generated.resources.num_of_products
import mahashin.composeapp.generated.resources.ordered
import mahashin.composeapp.generated.resources.products
import mahashin.composeapp.generated.resources.riyal_price
import mahashin.composeapp.generated.resources.shipment_fees
import mahashin.composeapp.generated.resources.total_price
import mahashin.composeapp.generated.resources.total_sell_price
import mahashin.composeapp.generated.resources.update_order
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OrderDetailsScreen(
    id: Int,
    onBackClick: () -> Unit,
    onOrderProductSelect : (Product?) -> Unit,
    onOrderEditProductSelect : (Int,Product?) -> Unit

) {
    val viewModel = koinViewModel<DetailsViewModel>()
    var nameField by viewModel.nameField
    var descriptionField by viewModel.descriptionField
    var dateField by viewModel.dateField
    var quantityField by viewModel.quantityField
    var totalBuyPriceField by viewModel.totalBuyPriceField
    var totalSellPriceField by viewModel.totalSellPriceField
    var totalShipmentFeeField by viewModel.totalShipmentFeeField
    var productsField by viewModel.productsField
    var riyalField by viewModel.riyalField

    val coroutineScope = rememberCoroutineScope()
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.fillMaxWidth(),
                backgroundColor = ColorPrimary,
                title = {
                    Text(
                        text = if (id == -1) stringResource(Res.string.create_order)
                        else "${stringResource(Res.string.update_order)} #($dateField)",
                        color = ColorWhite
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back arrow icon",
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            if (id == -1) {
                                viewModel.insertOrder(
                                    onSuccess = onBackClick,
                                    onError = { println(it) }
                                )
                            } else {
                                viewModel.updateOrder(
                                    onSuccess = onBackClick,
                                    onError = { println(it) }
                                )
                            }
                        }

                    }) {
                        Icon(
                            imageVector = if (id == -1) Icons.Default.Add
                            else Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.White, OFFWhite) // Customize colors
                    )
                )
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                )
                .padding(all = 12.dp)
                .verticalScroll(rememberScrollState()) // Make content scrollable
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = nameField,
                onValueChange = { nameField = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text =stringResource(Res.string.name)) }, // Outer hint
                placeholder = { Text(text =stringResource(Res.string.name)) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = descriptionField,
                onValueChange = { descriptionField = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text =stringResource(Res.string.description)) }, // Outer hint
                placeholder = { Text(text =stringResource(Res.string.description)) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = dateField,
                onValueChange = { dateField = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                placeholder = { Text(text = stringResource(Res.string.ordered)) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = totalBuyPriceField,
                onValueChange = { totalBuyPriceField = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text = stringResource(Res.string.total_price)) }, // Outer hint
                placeholder = { Text(text = stringResource(Res.string.total_price)) }
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = totalSellPriceField,
                onValueChange = { totalSellPriceField = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text = stringResource(Res.string.total_sell_price)) }, // Outer hint
                placeholder = { Text(text = stringResource(Res.string.total_sell_price)) }
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = totalShipmentFeeField,
                onValueChange = { totalShipmentFeeField = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text = stringResource(Res.string.shipment_fees)) },
                placeholder = { Text(text = stringResource(Res.string.shipment_fees)) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = quantityField,
                onValueChange = { quantityField = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                label = { Text(text = stringResource(Res.string.num_of_products)) }, // Outer hint
                placeholder = { Text(text = stringResource(Res.string.num_of_products)) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = riyalField,
                onValueChange = { riyalField = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                label = { Text(text = stringResource(Res.string.riyal_price)) }, // Outer hint
                placeholder = { Text(text = stringResource(Res.string.riyal_price)) }
            )

            Spacer(modifier = Modifier.height(12.dp))
            if (id != -1) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(Res.string.products), color = ColorBlack)
                    IconButton(onClick = {
                        onOrderProductSelect(Product())

                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }
                if (productsField.isEmpty()) {
                    ErrorView(message = stringResource(Res.string.no_products))
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth().height(500.dp).padding(padding)) {
                        items(productsField) {
                            ProductCard(cardData = it,
                                onClick = {
                                    onOrderEditProductSelect(id,it)
                                },
                                onDelete = { id ->
                                    showDialog.value = !showDialog.value
                                })
                            ConfirmationDialog(
                                title = stringResource(Res.string.delete_product_title),
                                message = stringResource(Res.string.delete_product_msg),
                                onConfirm = {
                                    coroutineScope.launch {
                                        viewModel.deleteProduct(id,it.id,
                                            onSuccess = {
                                                showDialog.value = !showDialog.value
                                            },
                                            onError = {
                                                showDialog.value = !showDialog.value
                                            }
                                        )
                                    }
                                },
                                onDismiss = {
                                    showDialog.value = !showDialog.value
                                },
                                showDialog = showDialog
                            )
                        }
                    }
                }
            }
        }
    }
}