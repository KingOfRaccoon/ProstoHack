package elements

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Serializable
data class VerticalPager(
    override val elements: List<UIElement>,
    override var ord: Int = 0,
    override val elementType: String = this::class.simpleName.orEmpty()
) : ListView {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun render(navigateTo: (String) -> Unit) {
        val state = rememberPagerState { elements.size }
        VerticalPager(
            state,
            Modifier.fillMaxWidth().padding(16.dp)
        ) {
            elements[it].render(navigateTo)
        }
    }
}