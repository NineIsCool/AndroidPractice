package com.example.androidpractice.domain.model

class MovieShort(
    val id: Int,
    val name: String,
    val type: String,
    val genres: List<Genre>,
    val poster: Poster,
)
