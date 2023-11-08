package io.github.shofucchi.network.api

import io.github.shofucchi.network.model.SearchedPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("search/photos")
    suspend fun getSearchedPhotos(
        @Query("query") query: String?,
        @Query("client_id") id: String?
    ): Response<SearchedPhoto>
}