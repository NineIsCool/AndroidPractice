package com.example.androidpractice.domain.repository

import com.example.androidpractice.data.api.MovieApi
import com.example.androidpractice.data.mapper.MovieMapper
import com.example.androidpractice.domain.model.Genre
import com.example.androidpractice.domain.model.MovieShort
import com.example.androidpractice.domain.model.MovieType
import com.example.androidpractice.domain.model.Poster
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.database.MovieDatabase
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.entity.MovieDbEntity

class MoviesRepository(
    private val api: MovieApi,
    private val mapper: MovieMapper,
    private val db: MovieDatabase
) : IMoviesRepository {

    override suspend fun getList(query: String, filterTypes: Set<MovieType>?) =
        withContext(Dispatchers.IO) {
            val response = api.searchMovies(query = query).search
                .orEmpty()
                .filter { movie ->
                    filterTypes.isNullOrEmpty()
                            || filterTypes.contains(MovieType.getMovieTypeByCode(movie.type))
                }
            mapper.toDomainList(response)
        }

    override suspend fun getById(id: Int) =
        withContext(Dispatchers.IO) {
            val response = api.getMovie(id)
            mapper.toDomain(response)
        }

    override suspend fun saveFavorite(movie: MovieShort) =
        withContext(Dispatchers.IO) {
            db.movieDao().insert(
                MovieDbEntity(
                    name = movie.name,
                    type = movie.type,
                    genre = movie.genres.getOrNull(0)?.name ?: "",
                    url = movie.poster.previewUrl
                )
            )
        }

    override suspend fun getFavorites() =
        withContext(Dispatchers.IO) {
            db.movieDao().getAll().map {
                MovieShort(
                    it.id ?: 0,
                    it.name.orEmpty(),
                    it.type.orEmpty(),
                    listOf(Genre.valueOf(it.genre.toString())),
                    Poster(it.url.orEmpty(), it.url.orEmpty())
                )
            }
        }
}