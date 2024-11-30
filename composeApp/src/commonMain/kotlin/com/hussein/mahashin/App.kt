
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.hussein.mahashin.presentation.navigation.SetupNavGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope

val navigationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {

            val navController = rememberNavController()
            SetupNavGraph(navController)

           /* Navigator(screen = Order()){ navigator ->
                Scaffold(
                    topBar = {
                        TopAppBar(modifier = Modifier.fillMaxWidth(), backgroundColor = Color(0xFF30D3A3)) { Text(text = stringResource(Res.string.app_name), fontSize = 20.sp, color = Color.White) }
                    },

                ) {innerPadding ->
                    SlideTransition(
                        navigator = navigator,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }*/
           /* val navController = rememberNavController()
           NavHost(
                navController = navController,
                startDestination = "screenA"
            ) {
                composable("screenA") {
                    val viewModel = koinViewModel<MainViewModel>()
                    val timer by viewModel.timer.collectAsState()
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = timer.toString()
                        )
                    }
                }
            }*/
        }
    }
}

@Composable
inline fun <reified T: ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}


