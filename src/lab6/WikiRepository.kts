import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WikiRepository {
    private val api: WikiApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://en.wikipedia.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(WikiApiService::class.java)
    }

    suspend fun hitCountCheck(name: String): WikiResponse {
        return api.getWikiHits(name)
    }
}
