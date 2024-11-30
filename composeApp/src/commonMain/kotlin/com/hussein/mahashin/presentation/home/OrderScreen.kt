package com.hussein.mahashin.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.hussein.mahashin.core.data.util.ColorPrimary
import com.hussein.mahashin.core.data.util.ColorWhite
import com.hussein.mahashin.core.data.util.DisplayResult
import com.hussein.mahashin.core.data.util.OFFWhite
import com.hussein.mahashin.presentation.compose.ConfirmationDialog
import com.hussein.mahashin.presentation.compose.ErrorView
import com.hussein.mahashin.presentation.compose.LoadingView
import koinViewModel
import kotlinx.coroutines.launch
import mahashin.composeapp.generated.resources.Res
import mahashin.composeapp.generated.resources.app_name
import mahashin.composeapp.generated.resources.delete_product_msg
import mahashin.composeapp.generated.resources.delete_product_title
import org.jetbrains.compose.resources.stringResource

/*class Order() : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        *//*  Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("ScreenA")
            Button(onClick = {
                navigator.push(OrderDetails("Hello World from ScreenA"))
            }){
                Text(text = "Navigate to ScreenB")
            }
        }*//*


    }
}*/

@Composable
fun OrderScreen(onOrderSelect: (Int) -> Unit,
                onCreateClick: (Int) -> Unit,
                onDeleteOrder: (Int) -> Unit) {

    val orderViewModel = koinViewModel<OrderViewModel>()
    val orders by orderViewModel.orders.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val showDialog = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.fillMaxWidth(), backgroundColor = ColorPrimary,
                title = { Text(text = stringResource(Res.string.app_name)) },
            )
        },
        contentColor = ColorWhite,
        floatingActionButton = {
            FloatingActionButton(onClick = { onCreateClick(-1) }, backgroundColor = ColorPrimary) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Icon"
                )
            }
        },
    ) {
        orders.DisplayResult(
            onLoading = { LoadingView() },
            onError = { ErrorView(it) },
            onSuccess = { data ->
                if (data.isNotEmpty()) {
                    LazyColumn (
                        modifier = Modifier.fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.White, OFFWhite) // Customize colors
                                )
                            )
                            .padding(all = 12.dp)
                            .padding(
                                top = it.calculateTopPadding(),
                                bottom = it.calculateBottomPadding()
                            ),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            items = data,
                            key = { it.id }
                        ) {
                            OrderView(
                                order = it,
                                onClick = {onOrderSelect(it.id)},
                                onDelete = {showDialog.value = !showDialog.value}
                            )
                            ConfirmationDialog(
                                title = stringResource(Res.string.delete_product_title),
                                message = stringResource(Res.string.delete_product_msg),
                                onConfirm = {
                                    coroutineScope.launch {
                                        orderViewModel.deleteOrder(it.id,
                                            onSuccess = {
                                                onDeleteOrder(it.id)
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
                } else {
                    ErrorView()
                }
            }
        )
    }
}