package alexander.voronov.marvelcharacters.data

import alexander.voronov.marvelcharacters.BuildConfig
import alexander.voronov.marvelcharacters.data.retrofit.MarvelApi
import alexander.voronov.marvelcharacters.domain.entities.MyResponse
import alexander.voronov.marvelcharacters.domain.repository.ItemsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest


// Your private key
private const val YOUR_PRIVATE_KEY = "Your private key"

// Your public key
private const val YOUR_PUBLIC_KEY = "Your public key"

class RetrofitItemsRepositoryImplementation : ItemsRepository {

    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://gateway.marvel.com/v1/")
        .client(OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()).build().create(MarvelApi::class.java)

    // Authorization on server

    private val currentTimestamp = System.currentTimeMillis().toString()
    private val digestParameters: String =
        currentTimestamp + BuildConfig.YOUR_PRIVATE_KEY + BuildConfig.YOUR_PUBLIC_KEY
    //currentTimestamp + YOUR_PRIVATE_KEY + YOUR_PUBLIC_KEY


    private val String.md5: String
        get() {
            val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
            return bytes.joinToString("") {
                "%02x".format(it)
            }
        }
    private val hashDigestParameters = digestParameters.md5
    private val limitResults: String = "100"

    override fun getItems(
        onSuccess: (MyResponse) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        api.listsOfComicsCharacters(
            limitResults,
            currentTimestamp,
            //YOUR_PUBLIC_KEY,
            BuildConfig.YOUR_PUBLIC_KEY,
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