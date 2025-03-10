import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EdustajaDao {
    @Query("SELECT DISTINCT puolue FROM edustaja")
    fun getPuolueet(): Flow<List<String>>

    @Query("SELECT * FROM edustaja WHERE puolue = :puolue")
    fun getEdustajat(puolue: String): Flow<List<Edustaja>>

    @Insert
    suspend fun insert(edustaja: Edustaja)

    @Delete
    suspend fun delete(edustaja: Edustaja)
}
