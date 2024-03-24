package elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
data class Row(
    override val texts: List<TextField>,
    override val edits: List<EditTextField>,
    override var buttons: List<Button>,
    override val images: List<Image>,
    override var ord: Int = 0,
    override val elementType: String = this::class.simpleName.orEmpty(),
    override val bottom: UIElement? = null
) : Container {

    init {
        buttons = buttons.map { it.copy(getMapData = ::getMapData) }
        setInButton(bottom)
    }

    private fun setInButton(bottom: UIElement?) {
        if (bottom is Container)
            bottom.buttons = bottom.buttons.map { it.copy(getMapData = ::getMapData) }
    }

    @Composable
    override fun render(navigateTo: (String) -> Unit) {
        Row(
            Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            getElements().forEach {
                it.render(navigateTo)
            }

            bottom?.render(navigateTo)
        }
    }
}