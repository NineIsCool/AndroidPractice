package com.example.androidpractice.domain.repository

import com.example.androidpractice.data.mock.MoviesData

class MoviesRepository : IMoviesRepository {
    override fun getList() = MoviesData.movies

    override fun getById(id: Int) = MoviesData.movies.find { it.id == id }
}