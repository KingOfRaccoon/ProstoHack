package elements

import kotlinx.serialization.Serializable

@Serializable
data class Navigation(val screens: List<Screen>, val startRoute: String)