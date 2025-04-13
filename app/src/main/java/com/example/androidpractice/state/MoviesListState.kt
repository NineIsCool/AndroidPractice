package com.example.androidpractice.state

import com.example.androidpractice.domain.model.MovieShort
import com.example.androidpractice.domain.model.MovieType

interface MoviesListState {
    val items: List<MovieShort>
    val query: String
    val isEmpty: Boolean
    val hasBadge: Boolean
    val showTypesDialog: Boolean
    val typesVariants: Set<MovieType>
    val selectedTypes: Set<MovieType>
    val isLoading: Boolean
    val error: String?
}