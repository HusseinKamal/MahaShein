import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController(
    configure = {
        com.hussein.mahashin.di.KoinInitializer().init()
    }
) {
    App()
}