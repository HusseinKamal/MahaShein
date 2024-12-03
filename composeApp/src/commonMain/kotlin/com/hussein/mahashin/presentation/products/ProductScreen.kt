package com.hussein.mahashin.presentation.products

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.hussein.mahashin.core.data.util.ColorPrimary
import com.hussein.mahashin.core.data.util.ColorWhite
import com.hussein.mahashin.core.data.util.OFFWhite
import com.hussein.mahashin.core.domain.Product
import kotlinx.coroutines.launch
import mahashin.composeapp.generated.resources.Res
import mahashin.composeapp.generated.resources.create_product
import mahashin.composeapp.generated.resources.description
import mahashin.composeapp.generated.resources.name
import mahashin.composeapp.generated.resources.num_of_products
import mahashin.composeapp.generated.resources.prepayment
import mahashin.composeapp.generated.resources.total_price
import mahashin.composeapp.generated.resources.total_sell_price
import mahashin.composeapp.generated.resources.update_product
import mahashin.composeapp.generated.resources.username
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    id: Int,
    product: Product?,
    onBackClick: () -> Unit) {
    val viewModel = koinViewModel<ProductViewModel>()

    val coroutineScope = rememberCoroutineScope()

    val productsField = viewModel.productField.collectAsState()

    // Initialize ViewModel state with the passed product
    LaunchedEffect(product) {
        if (product != null) {
            viewModel.initializeProduct(product)
        }

    }
   Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.fillMaxWidth(), backgroundColor = ColorPrimary,
                title = {
                    Text(
                        text = if (product?.id == 0) stringResource(Res.string.create_product)
                        else " ${stringResource(Res.string.update_product)} #(${product?.name})",
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
                            if (product?.id == 0) {
                                viewModel.insertProduct(productsField.value,
                                    onSuccess = onBackClick,
                                    onError = { println(it) }
                                )
                            } else {
                                viewModel.updateProduct(productsField.value,
                                    onSuccess = onBackClick,
                                    onError = { println(it) }
                                )
                            }
                        }

                    }) {
                        Icon(
                            imageVector = if (product == null) Icons.Default.Add
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
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.White, OFFWhite) // Customize colors
                    )
                )
                .fillMaxSize()
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                )
                .padding(all = 12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = productsField.value.name ?: "",
                onValueChange = { viewModel.onProductNameChange(it) },
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
                value =  productsField.value.description ?: "",
                onValueChange = {
                    viewModel.onProductDescriptionChange(it)
                                },
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
                value =  productsField.value.priceBuy ?: "",
                onValueChange = {viewModel.onProductPriceBuyChange(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text =stringResource(Res.string.total_price)) }, // Outer hint
                placeholder = { Text(text = stringResource(Res.string.total_price)) }
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value =  productsField.value.priceSell ?: "",
                onValueChange = { viewModel.onProductPriceSellChange(it)},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text =stringResource(Res.string.total_sell_price)) }, // Outer hint
                placeholder = { Text(text = stringResource(Res.string.total_sell_price)) }
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value =  productsField.value.username ?: "",
                onValueChange = { viewModel.onProductUsernameChange(it)},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text =stringResource(Res.string.username)) }, // Outer hint
                placeholder = { Text(text = stringResource(Res.string.username)) }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value =  productsField.value.prepayment ?: "",
                onValueChange = { viewModel.onProductPrepaymentChange(it)},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text =stringResource(Res.string.prepayment)) }, // Outer hint
                placeholder = { Text(text = stringResource(Res.string.prepayment)) }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

