package elements

import DataViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject
import util.Resource

@Serializable
data class Card(
    override val texts: List<TextField> = listOf(),
    override val edits: List<EditTextField> = listOf(),
    override var buttons: List<Button> = listOf(),
    override val images: List<Image> = listOf(),
    override val route: String,
    override val routeNavigation: String,
    override val type: String,
    override var ord: Int = 0,
    override val elementType: String = this::class.simpleName.orEmpty(),
    override val bottom: UIElement? = null
) : Container, UIClick {

    init {
        buttons = buttons.map { it.copy(getMapData = ::getMapData) }
        setInButton(bottom)
    }
    private fun setInButton(bottom: UIElement?) {
        if (bottom is Container)
            bottom.buttons = bottom.buttons.map { it.copy(getMapData = ::getMapData) }
    }

    override fun onClick(
        navigateTo: (String) -> Unit,
        function: (String, Map<String, String>) -> Resource<String>
    ) {
        when (type) {
            TypeButton.Create.name -> {

            }

            TypeButton.Navigate.name -> {

            }

            TypeButton.Update.name -> {

            }

            TypeButton.Delete.name -> {

            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterialApi::class)
    override fun render(navigateTo: (String) -> Unit) {
        val dataViewModel = koinInject<DataViewModel>()
        Card({
            onClick({ navigateTo(route) }) { route: String, data: Map<String, String> ->
                runBlocking {
                    dataViewModel.load(
                        route,
                        data
                    )
                }
            }
        }, Modifier.fillMaxWidth()) {
            Column(
                Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                getElements().forEach {
                    it.render(navigateTo)
                }
                bottom?.render(navigateTo)
            }
        }
    }
}