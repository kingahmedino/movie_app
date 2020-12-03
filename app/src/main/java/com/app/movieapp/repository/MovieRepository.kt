package com.app.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.movieapp.api.BackendAPI
import com.app.movieapp.models.Actor
import com.app.movieapp.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object MovieRepository {

    suspend fun getMovies(page: Int = 1): List<Movie> {
        val response = BackendAPI.invoke().searchPopularMovies(page)
        return response.results
    }

    suspend fun getMovieCast(movie_id: Int): List<Actor> {
        val response = BackendAPI.invoke().movieCast(movie_id)
        return response.cast
    }
}