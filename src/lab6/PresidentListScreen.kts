import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PresidentListScreen(viewModel: MyViewModel = viewModel()) {
    var selectedPresident by remember { mutableStateOf<President?>(null) }

    Column {
        LazyColumn {
            items(DataProvider.presidents) { president ->
                Text(
                    text = "${president.name} (${president.startDuty} - ${president.endDuty})",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            selectedPresident = president
                            viewModel.getHits(president.name)
                        }
                )
            }
        }

        selectedPresident?.let { president ->
            PresidentDetailScreen(president, viewModel.wikiUiState)
        }
    }
}

@Composable
fun PresidentDetailScreen(president: President, hitCount: Int) {
    Column(Modifier.padding(16.dp)) {
        Text(text = "Nimi: ${president.name}", style = MaterialTheme.typography.headlineSmall)
        Text(text = "Kaudet: ${president.startDuty} - ${president.endDuty}")
        Text(text = "Kuvaus: ${president.description}")
        Text(text = "Wikipedia-linkkien määrä: $hitCount")
    }
}
