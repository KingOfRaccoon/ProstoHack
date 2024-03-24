package elements

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.serialization.Serializable

@Serializable
data class EditTextField(
    var value: String,
    val key: String,
    val hint: String,
    override var ord: Int,
    override val elementType: String = this::class.simpleName.orEmpty()
) : UIElement {
    @Composable
    override fun render(navigateTo: (String) -> Unit) {
        val text = remember {  mutableStateOf(value) }
        TextField(text.value, { this.value = it; text.value = it }, label = { Text(hint) })
    }
}
