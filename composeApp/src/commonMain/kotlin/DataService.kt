import elements.FlowNavigation
import io.ktor.http.Parameters
import util.Postman
import util.Resource

class DataService(private val postman: Postman) {
    private val baseUrl = "https://humorous-ringtail-abnormally.ngrok-free.app/"
    private val tagNavigation = "getStartFlow"

    suspend fun getNavigation(): Resource<FlowNavigation> {
        return postman.get(baseUrl, tagNavigation, arguments = mapOf("param" to typeDevice))
    }

    suspend fun get(route: String, mapData: Map<String, String>): Resource<String> {
        return postman.submitForm(baseUrl, route, Parameters.build {
            mapData.forEach {
                append(it.key, it.value)
            }
        })
    }
}