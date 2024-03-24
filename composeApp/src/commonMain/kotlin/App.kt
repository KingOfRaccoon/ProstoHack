import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import elements.CustomAlertDialog
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject

@Composable
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()
        val viewModel = koinInject<DataViewModel>()
        val navigation = viewModel.navigationFlow.collectAsState()
        navigation.value.data?.let {
            MaterialTheme {
                NavHost(navigator, it.startRoute) {
                    it.flows.forEach { flow ->
                        println(flow.routeFlow)
                        group(flow.routeFlow, flow.startRoute) {
                            flow.screens.forEach { screen ->
                                scene(screen.route){
                                    screen.content.render { navigator.navigate(it) }
                                }
                            }
                        }
                    }

                    dialog("error/{errorDesc}") {
                        CustomAlertDialog(it.path<String>("errorDesc", "").orEmpty())
                    }
                }
            }
        }
    }
}