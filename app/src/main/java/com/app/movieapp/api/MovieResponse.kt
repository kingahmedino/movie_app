package com.app.movieapp.api

import com.app.movieapp.models.Movie

data class MovieResponse(
    val results: List<Movie>
)