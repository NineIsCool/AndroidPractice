package com.example.androidpractice.dto

import com.example.androidpractice.domain.model.Poster
import com.google.gson.annotations.SerializedName

class MoviesSearchResponse(
    @SerializedName("docs")
    val search: List<MovieShortResponse>?
)

class MovieShortResponse(
    val id: Int?,
    val name: String?,
    val year: String?,
    val type: String?,
    val poster: Poster?,
)