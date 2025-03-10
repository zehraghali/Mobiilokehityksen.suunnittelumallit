import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Edustaja(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nimi: String,
    val puolue: String
)
