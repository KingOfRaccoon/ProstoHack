package elements

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
data class TextField(
    val value: String,
    override val ord: Int,
    override val elementType: String = this::class.simpleName.orEmpty()
) : UIElement {

    @Composable
    override fun render(navigateTo: (String) -> Unit) {
        Text(value)
    }
}
