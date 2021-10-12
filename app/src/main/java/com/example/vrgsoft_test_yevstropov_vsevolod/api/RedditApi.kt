package com.example.vrgsoft_test_yevstropov_vsevolod.api
import com.example.vrgsoft_test_yevstropov_vsevolod.dto.RedditApiDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val logging = HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
private val httpClientBuilder = OkHttpClient.Builder().apply { addInterceptor(logging) }

var BASE_URL:String = "https://www.reddit.com/"

var retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(httpClientBuilder.build())
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface RedditApiService {

    @GET("/top.json") //
    suspend fun getListRedditApiDto(): Response<RedditApiDto>

}

object RedditApi {
    val retrofitService : RedditApiService by lazy {
        retrofit.create(RedditApiService::class.java) }}