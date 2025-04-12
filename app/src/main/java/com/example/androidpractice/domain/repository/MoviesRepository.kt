package com.example.androidpractice.domain.repository

import com.example.androidpractice.data.api.MovieApi
import com.example.androidpractice.data.mapper.MovieMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val api: MovieApi,
    private val mapper: MovieMapper
) : IMoviesRepository {

    override suspend fun getList(query: String) =
        withContext(Dispatchers.IO) {
            val response = api.searchMovies(query = query)
            mapper.toDomainList(response)
        }

    override suspend fun getById(id: Int) =
        withContext(Dispatchers.IO) {
            val response = api.getMovie(id)
            mapper.toDomain(response)
        }
}