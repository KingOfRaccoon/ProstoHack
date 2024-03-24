package elements

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val url: String,
    override var ord: Int,
    override val elementType: String = this::class.simpleName.orEmpty()
) : UIElement {
    @Composable
    override fun render(navigateTo: (String) -> Unit) {
        KamelImage(asyncPainterResource(url), null, Modifier.fillMaxSize(0.2f))
    }
}