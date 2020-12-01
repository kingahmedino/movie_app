package com.app.movieapp.screens.home

import androidx.lifecycle.ViewModel
import com.app.movieapp.models.Actor
import com.app.movieapp.repository.MovieRepository

class HomeViewModel: ViewModel() {
    val movies = MovieRepository.getMovies()

    fun getMovieActors(movie_id: Int): List<Actor>{
        return MovieRepository.getMovieCast(movie_id)
    }
}