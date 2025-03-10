import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getDatabase(applicationContext)
        setContent {
            val viewModel = EduskuntaViewModel(database)
            EduskuntaScreen(viewModel)
        }
    }
}

@Composable
fun EduskuntaScreen(viewModel: EduskuntaViewModel) {
    val puolueet by viewModel.puolueet.collectAsState(initial = emptyList())
    val edustajat by viewModel.edustajat.collectAsState(initial = emptyList())

    Column {
        LazyColumn {
            items(puolueet) { puolue ->
                Text(puolue, modifier = Modifier.clickable { viewModel.selectPuolue(puolue) })
            }
        }

        LazyColumn {
            items(edustajat) { edustaja ->
                Row {
                    Text(edustaja.nimi)
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = { viewModel.removeEdustaja(edustaja) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Poista")
                    }
                }
            }
        }
    }
}
