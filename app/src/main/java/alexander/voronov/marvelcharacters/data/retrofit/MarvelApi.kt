package alexander.voronov.marvelcharacters.data.retrofit

import alexander.voronov.marvelcharacters.domain.entities.MyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("public/characters")
    fun listsOfComicCharacters(
        @Query("limit") limitResults: String,
        @Query("ts") timeStamp: String,
        @Query("apikey") key: String,
        @Query("hash") digest: String
    ): Call<MyResponse>
}