package io.github.shofucchi.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchedPhoto(
    val results: List<Result>
)

@Serializable
data class Result(
    val urls: Url
)

@Serializable
data class Url(
    val regular: String
)