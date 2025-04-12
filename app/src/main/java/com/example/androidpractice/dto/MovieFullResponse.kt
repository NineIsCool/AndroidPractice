package com.example.androidpractice.dto

import androidx.annotation.Keep
import com.example.androidpractice.domain.model.Movie.Country
import com.example.androidpractice.domain.model.Movie.Genre
import com.example.androidpractice.domain.model.Movie.Premiere
import com.example.androidpractice.domain.model.Person
import com.example.androidpractice.domain.model.Poster
import com.example.androidpractice.domain.model.Rating
import com.example.androidpractice.domain.model.Votes

@Keep
class MovieFullResponse(
    val id: Int?,
    val name: String?,
    val rating: Rating?,
    val description: String?,
    val votes: Votes?,
    val premiere: Premiere?,
    val poster: Poster?,
    val genres: List<Genre>?,
    val countries: List<Country>?,
    val persons: List<Person>?
)