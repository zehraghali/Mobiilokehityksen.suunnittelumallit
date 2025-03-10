import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val repository: WikiRepository = WikiRepository()

    var wikiUiState: Int by mutableStateOf(0)
        private set

    fun getHits(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val serverResp = repository.hitCountCheck(name)
            wikiUiState = serverResp.query.searchinfo.totalhits
        }
    }
}
