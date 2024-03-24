package elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val url: String,
    override val ord: Int,
    override val elementType: String = this::class.simpleName.orEmpty()
) : UIElement {
    @Composable
    override fun render(navigateTo: (String) -> Unit) {
        KamelImage(asyncPainterResource(url), null, Modifier.fillMaxWidth())
    }
}