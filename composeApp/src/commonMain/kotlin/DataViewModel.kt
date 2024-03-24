import elements.FlowNavigation
import elements.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import util.Resource

class DataViewModel(private val dataRepository: DataRepository) {
    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
        prettyPrint = true
        coerceInputValues = true
        useArrayPolymorphism = true
    }
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val navigationFlow =
        MutableStateFlow<Resource<Navigation>>(Resource.Loading())

    private fun loadNavigation() {
        coroutineScope.launch {
            navigationFlow.update {
                when (val flow = dataRepository.getNavigation()) {
                    is Resource.Error -> Resource.Error(flow.message, flow.status)
                    is Resource.Loading -> Resource.Loading()
                    is Resource.Success -> Resource.Success(
                        Navigation(
                            listOf(flow.data),
                            flow.data.routeFlow
                        )
                    )
                }
            }

        }
    }

    suspend fun load(route: String, data: Map<String, String>): Resource<String> {
        return runBlocking {
            val result = dataRepository.get(route, data)
            result.also {
                if (it is Resource.Success) {
                    try {
                        val flow = json.decodeFromString(FlowNavigation.serializer(), it.data)
                        navigationFlow.emit(
                            Resource.Success(
                                Navigation(
                                    navigationFlow.value.data?.flows.orEmpty() + flow,
//                                    navigationFlow.value.data?.startRoute.orEmpty()
                                    flow.startRoute
                                )
                            )
                        )
                        println("update")
                        println(navigationFlow.value.data?.flows?.size)
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            }

            return@runBlocking result
        }
    }

    init {
        loadNavigation()
    }
}