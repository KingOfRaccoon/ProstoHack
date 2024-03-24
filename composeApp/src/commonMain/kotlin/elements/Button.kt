package elements

import DataViewModel
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject
import util.Resource

@Serializable
data class Button(
    val text: String,
    override val route: String,
    override val routeNavigation: String,
    override val type: String,
    override var ord: Int,
    val getMapData: () -> Map<String, String> = { mapOf() },
    override val elementType: String = this::class.simpleName.orEmpty()
) : UIElement, UIClick {
    override fun onClick(
        navigateTo: (String) -> Unit,
        function: (String, Map<String, String>) -> Resource<String>
    ) {
        when (type) {
            TypeButton.Create.name -> {
//                create(getMapData().toString())
            }

            TypeButton.Navigate.name -> {
                navigateTo(routeNavigation)
            }

            TypeButton.Create.name + "/" + TypeButton.Navigate.name -> {
                MainScope().launch {
                    val request = function(route, getMapData())
                    if (request.status.also { println("status: $it") } < 300) {
//                        navigateTo(routeNavigation)
                    } else {
                        navigateTo("error/$request")
                    }
                }
            }

            TypeButton.Update.name -> {
            }

            TypeButton.Delete.name -> {
            }
        }
    }

    @Composable
    override fun render(navigateTo: (String) -> Unit) {
        val dataViewModel = koinInject<DataViewModel>()
        Button({
            onClick(
                { navigateTo(it) }
            ) { route: String, data: Map<String, String> ->
                runBlocking {
                    dataViewModel.load(
                        route,
                        data
                    ).also {
                        println("return")
                    }
                }
            }
        }) {
            Text(text)
        }
    }
}
