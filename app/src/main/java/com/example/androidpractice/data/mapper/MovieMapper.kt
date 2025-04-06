package com.example.androidpractice.data.mapper

import com.example.androidpractice.domain.model.Movie
import com.example.androidpractice.domain.model.MovieShort
import com.example.androidpractice.domain.model.Poster
import com.example.androidpractice.domain.model.Rating
import com.example.androidpractice.domain.model.Votes
import com.example.androidpractice.dto.MovieFullResponse
import com.example.androidpractice.dto.MovieShortResponse
import com.example.androidpractice.dto.MoviesSearchResponse

class MovieMapper {
    fun toDomain(response: MovieFullResponse): Movie? {
        if (response.id == null || response.name == null) return null

        return Movie(
            id = response.id.or(0),
            name = response.name,
            rating = response.rating ?: Rating(0.0, 0.0, 0.0),
            description = response.description.orEmpty(),
            votes = response.votes ?: Votes(0, 0, 0),
            premiere = response.premiere ?: Movie.Premiere(""),
            poster = response.poster ?: Poster("", ""),
            genres = response.genres ?: emptyList(),
            countries = response.countries ?: emptyList(),
            persons = response.persons ?: emptyList()
        )
    }

    fun toDomain(response: MovieShortResponse): MovieShort {
        return MovieShort(
            id = response.id ?: 0,
            name = response.name.orEmpty(),
            poster = response.poster ?: Poster("", "")
        )
    }

    fun toDomainList(response: MoviesSearchResponse) =
        response.search?.map { movie -> toDomain(movie) }.orEmpty()
} 