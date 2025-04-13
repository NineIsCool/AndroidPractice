package com.example.androidpractice.data.mapper

import com.example.androidpractice.domain.model.Genre
import com.example.androidpractice.domain.model.Movie
import com.example.androidpractice.domain.model.MovieShort
import com.example.androidpractice.domain.model.MovieType
import com.example.androidpractice.domain.model.Poster
import com.example.androidpractice.domain.model.Rating
import com.example.androidpractice.domain.model.Votes
import com.example.androidpractice.dto.MovieFullResponse
import com.example.androidpractice.dto.MovieShortResponse
import com.example.androidpractice.dto.MoviesSearchResponse

class MovieMapper {
    fun toDomain(response: MovieFullResponse): Movie? {
        if (response.id == null
            || response.name.isNullOrBlank()
        ) return null

        return Movie(
            id = response.id.or(0),
            name = response.name,
            type = MovieType.getMovieTypeByCode(response.type),
            rating = response.rating ?: Rating(0.0, 0.0, 0.0),
            description = response.description.orEmpty(),
            votes = response.votes ?: Votes(0, 0, 0),
            premiere = response.premiere?.world ?: "",
            poster = response.poster ?: Poster("", ""),
            genres = response.genres?.map { genre -> Genre.getGenreByName(genre.name) }
                ?: emptyList(),
            countries = response.countries?.map { country -> country.name } ?: emptyList(),
            persons = response.persons ?: emptyList()
        )
    }

    fun toDomain(response: MovieShortResponse): MovieShort {
        return MovieShort(
            id = response.id ?: 0,
            name = response.name.orEmpty(),
            type = response.type.orEmpty(),
            genres = response.genres?.map { genre -> Genre.getGenreByName(genre.name) }
                ?: emptyList(),
            poster = response.poster ?: Poster("", "")
        )
    }

    fun toDomainList(response: List<MovieShortResponse>) =
        response.filter { movie -> movie.id != null && !movie.name.isNullOrBlank() }
            .map { movie -> toDomain(movie) }
} 