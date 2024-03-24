package elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
data class Column(
    override val texts: List<TextField>,
    override val edits: List<EditTextField>,
    override var buttons: List<Button>,
    override val images: List<Image>,
    override val ord: Int = 0,
    override val elementType: String = this::class.simpleName.orEmpty()
) : Container {

    init {
        buttons = buttons.map { it.copy(getMapData = ::getMapData) }
    }

    @Composable
    override fun render(navigateTo: (String) -> Unit) {
        Column(
            Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            getElements().forEach {
                it.render(navigateTo)
            }
        }
    }
}