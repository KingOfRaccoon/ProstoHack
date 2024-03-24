package elements

import kotlinx.serialization.Serializable

@Serializable
data class Screen(
    val content: ListView,
    val title: String,
    val route: String,
    val parameters: List<String> = listOf()
)