package io.github.shofucchi.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideJson() = Json { ignoreUnknownKeys = true}

    @Provides
    fun builder() = Retrofit.Builder()

    @Provides
    fun ioDispatchers() = Dispatchers.IO
}