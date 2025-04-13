package com.example.androidpractice.dto

import androidx.annotation.Keep
import com.example.androidpractice.domain.model.Person
import com.example.androidpractice.domain.model.Poster
import com.example.androidpractice.domain.model.Rating
import com.example.androidpractice.domain.model.Votes

@Keep
class MovieFullResponse(
    val id: Int?,
    val name: String?,
    val type: String?,
    val rating: Rating?,
    val description: String?,
    val votes: Votes?,
    val premiere: PremiereDto?,
    val poster: Poster?,
    val genres: List<GenreDto>?,
    val countries: List<CountryDto>?,
    val persons: List<Person>?
){
    class PremiereDto(
        val world: String
    )

    class GenreDto(
        val name: String
    )

    class CountryDto(
        val name: String
    )
}