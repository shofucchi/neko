package io.github.shofucchi.network

import android.util.Log
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.shofucchi.network.api.UnsplashApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class RetrofitService @Inject constructor(
    retrofit: Retrofit,
    private val ioDispatcher: CoroutineDispatcher
) {

    private val api = retrofit.create(UnsplashApi::class.java)

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