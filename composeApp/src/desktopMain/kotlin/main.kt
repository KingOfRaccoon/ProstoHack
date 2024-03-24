import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.getModules
import di.initKoin
import org.koin.compose.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "ProstoHack") {
        KoinApplication({ modules(getModules()) }) {
            App()
        }
    }
}