package com.example.androidpractice.domain.model

data class Movie(
    val id: Int = 0,
    val name: String = "",
    val rating: Rating = Rating(0.0, 0.0, 0.0),
    val description: String = "",
    val votes: Votes = Votes(0, 0, 0),
    val premiere: Premiere = Premiere(""),
    val poster: Poster = Poster("", ""),
    val genres: List<Genre> = listOf(),
    val countries: List<Country> = listOf(),
    val persons: List<Person> = listOf()
) {

    @JvmInline
    value class Premiere(
        val world: String
    )

    @JvmInline
    value class Genre(
        val name: String
    )

    @JvmInline
    value class Country(
        val name: String
    )
}