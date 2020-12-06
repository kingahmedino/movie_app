package com.app.movieapp.models

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val placeHolder: Int,
    val backdrop_path: String?,
    val overview: String?,
    val genre_ids: List<Int>,
    var actors: List<Actor>?
)