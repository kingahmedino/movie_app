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
    private val movieLiveData = MutableLiveData<List<Movie>>()
    private lateinit var actors: List<Actor>

    fun getMovies(page: Int = 1): LiveData<List<Movie>> {
        GlobalScope.launch(Dispatchers.IO) {
            val response = BackendAPI.invoke().searchPopularMovies(page)
            val movies = response.results
            movieLiveData.postValue(movies)
        }
        return movieLiveData
    }

    fun getMovieCast(movie_id: Int): List<Actor>{
        GlobalScope.launch(Dispatchers.IO){
            val response = BackendAPI.invoke().movieCast(movie_id)
            actors = response.cast
        }
        return actors
    }
}