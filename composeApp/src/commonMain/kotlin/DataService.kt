import elements.Navigation
import io.ktor.http.Parameters
import util.Postman
import util.Resource

class DataService(private val postman: Postman) {
    val baseUrl = "https://humorous-ringtail-abnormally.ngrok-free.app/"
    val tagNavigation = ""

    suspend fun getNavigation(): Resource<Navigation> {
        return postman.get(baseUrl, tagNavigation)
    }

    suspend fun get(route: String, mapData: Map<String, String>): Resource<String> {
        return postman.submitForm(baseUrl, route, Parameters.build {
            mapData.forEach {
                append(it.key, it.value)
            }
        })
    }
}