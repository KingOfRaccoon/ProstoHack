package elements

import androidx.compose.runtime.Composable


interface Container: UIElement {
    val texts: List<TextField>
    val edits: List<EditTextField>
    var buttons: List<Button>
    val images: List<Image>
    val bottom: UIElement?
    override val elementType: String

    @Composable
    override fun render(navigateTo: (String) -> Unit)

    fun getElements() = (texts + edits + buttons + images).sortedBy { it.ord }
    fun getMapData() = edits.associate { it.key to it.value }
}