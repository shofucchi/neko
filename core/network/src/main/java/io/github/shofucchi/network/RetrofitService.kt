package io.github.shofucchi.network

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.github.shofucchi.network.api.UnsplashApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

private const val UNSPLASH_API_URL = "https://api.unsplash.com/"

object RetrofitService {

    private val json = Json { ignoreUnknownKeys = true }

    private val api = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(UNSPLASH_API_URL)
        .build()
        .create(UnsplashApi::class.java)

    suspend fun sync(): Boolean {
        val searchPhotos = getSearchedPhotos().body()
        Log.d("debug", "${searchPhotos?.results}")
        return getSearchedPhotos().isSuccessful
    }

    private suspend fun getSearchedPhotos() =
        api.getSearchedPhotos(query = "cat", id = BuildConfig.API_KEY)
}