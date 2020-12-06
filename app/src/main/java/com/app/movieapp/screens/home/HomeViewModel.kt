package com.app.movieapp.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.movieapp.models.Actor
import com.app.movieapp.models.Movie
import com.app.movieapp.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val movies = MutableLiveData<List<Movie>>()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        getMovies()
    }

    private fun getMovies(){
        uiScope.launch{
            movies.postValue(MovieRepository.getMovies())
        }
    }

//    fun getMovieActors(movie_id: Int): List<Actor>{
//        viewModelScope.launch(Dispatchers.Default){
//            return@launch MovieRepository.getMovieCast(movie_id)
//        }
//    }
}