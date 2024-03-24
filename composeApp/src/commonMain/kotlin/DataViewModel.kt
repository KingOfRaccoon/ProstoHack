import elements.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import util.Resource

class DataViewModel(private val dataRepository: DataRepository) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val navigationFlow =
        MutableStateFlow<Resource<Navigation>>(Resource.Loading())

    private fun loadNavigation() {
        coroutineScope.launch {
            navigationFlow.update { dataRepository.getNavigation() }
        }
    }

    init {
        loadNavigation()
    }
}