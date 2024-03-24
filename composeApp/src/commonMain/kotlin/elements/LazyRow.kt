package elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
data class LazyRow(
    override val elements: List<UIElement>,
    override val ord: Int = 0,
    override val elementType: String = this::class.simpleName.orEmpty()
) : ListView {
    @Composable
    override fun render(navigateTo: (String) -> Unit) {
        LazyRow(
            Modifier.fillMaxSize().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(elements) {
                it.render(navigateTo)
            }
        }
    }
}