package elements

import util.Resource

interface UIClick {
    val route: String
    val routeNavigation: String
    val type: String

    fun onClick(
        navigateTo: (String) -> Unit,
        function: (String, Map<String, String>) -> Resource<String>
    )
}