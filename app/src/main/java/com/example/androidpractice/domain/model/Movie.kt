package com.example.androidpractice.domain.model

data class Movie(
    val id: Int,
    val name: String,
    val type: MovieType,
    val rating: Rating = Rating(0.0, 0.0, 0.0),
    val description: String = "",
    val votes: Votes = Votes(0, 0, 0),
    val premiere: String = "",
    val poster: Poster = Poster("", ""),
    val genres: List<Genre> = listOf(),
    val countries: List<String> = listOf(),
    val persons: List<Person> = listOf()
)