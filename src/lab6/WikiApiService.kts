import retrofit2.http.GET
import retrofit2.http.Query

data class WikiResponse(val query: QueryData)
data class QueryData(val searchinfo: SearchInfo)
data class SearchInfo(val totalhits: Int)

interface WikiApiService {
    @GET("w/api.php?action=query&format=json&list=search")
    suspend fun getWikiHits(@Query("srsearch") query: String): WikiResponse
}
