import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class EduskuntaViewModel(private val database: AppDatabase) : ViewModel() {
    private val dao = database.edustajaDao()
    val puolueet: Flow<List<String>> = dao.getPuolueet()

    private val _selectedPuolue = mutableStateOf<String?>(null)
    val selectedPuolue: State<String?> = _selectedPuolue

    val edustajat: Flow<List<Edustaja>> = dao.getEdustajat(_selectedPuolue.value ?: "")

    fun selectPuolue(puolue: String) {
        _selectedPuolue.value = puolue
    }

    fun addEdustaja(nimi: String, puolue: String) {
        viewModelScope.launch { dao.insert(Edustaja(nimi = nimi, puolue = puolue)) }
    }

    fun removeEdustaja(edustaja: Edustaja) {
        viewModelScope.launch { dao.delete(edustaja) }
    }
}
