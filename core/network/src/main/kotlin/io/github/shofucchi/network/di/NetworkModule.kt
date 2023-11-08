package io.github.shofucchi.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val json = Json { ignoreUnknownKeys = true}

    private const val URL = "https://api.unsplash.com/"

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(URL)
        .build()

    @Provides
    fun provideIoDispatchers() = Dispatchers.IO
}