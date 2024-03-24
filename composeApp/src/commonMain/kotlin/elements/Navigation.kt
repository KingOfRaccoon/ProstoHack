package elements

import kotlinx.serialization.Serializable

@Serializable
data class Navigation(val flows: List<FlowNavigation>, val startRoute: String)

@Serializable
data class FlowNavigation(val screens: List<Screen>, val startRoute: String, val routeFlow: String)