package io.github.shofucchi.network

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.shofucchi.network.api.UnsplashApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit.Builder
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class RetrofitService @Inject constructor(
    json: Json,
    builder: Builder,
    private val ioDispatcher: CoroutineDispatcher
) {

    private val api = builder
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BuildConfig.API_URL)
        .build()
        .create(UnsplashApi::class.java)

    suspend fun sync(): Boolean {
        return withContext(ioDispatcher) {
            val searchPhotos = getSearchedPhotos().body()
            Log.d("debug", "${searchPhotos?.results}")
            getSearchedPhotos().isSuccessful
        }
    }

    private suspend fun getSearchedPhotos() =
        api.getSearchedPhotos(query = "cat", id = BuildConfig.API_KEY)
}