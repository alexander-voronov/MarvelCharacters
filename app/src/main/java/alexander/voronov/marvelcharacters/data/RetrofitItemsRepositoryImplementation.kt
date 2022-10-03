package alexander.voronov.marvelcharacters.data

import alexander.voronov.marvelcharacters.domain.repository.ItemsRepository
import alexander.voronov.marvelcharacters.domain.entities.MyResponse
import alexander.voronov.marvelcharacters.data.retrofit.MarvelApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest


class RetrofitItemsRepositoryImplementation : ItemsRepository {

    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://gateway.marvel.com/v1/")
        .client(OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()).build().create(MarvelApi::class.java)

    //authorizationOnServer

    private val currentTimestamp = System.currentTimeMillis().toString()
    private val digestParameters: String =
        currentTimestamp + "Your private key" + "Your public key"
    private val String.md5: String
        get() {
            val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
            return bytes.joinToString("") {
                "%02x".format(it)
            }
        }
    private val hashDigestParameters: String = digestParameters.md5
    private val limitResults: String = "100"

    override fun getItems(
        onSuccess: (MyResponse) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        api.listsOfComicCharacters(
            limitResults,
            currentTimestamp,
            "Your public key",
            hashDigestParameters
        )
            .enqueue(object : Callback<MyResponse> {
                override fun onResponse(
                    call: Call<MyResponse>,
                    response: Response<MyResponse>
                ) {
                    val body = response.body()
                    if (response.isSuccessful && body != null) {
                        onSuccess.invoke(body)
                    } else {
                        onError?.invoke(IllegalStateException("No data or error!"))
                    }
                }

                override fun onFailure(
                    call: Call<MyResponse>,
                    t: Throwable
                ) {
                    onError?.invoke(t)
                }
            })
    }
}