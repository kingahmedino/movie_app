package com.app.movieapp.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movieapp.models.Actor
import com.app.movieapp.models.Movie
import com.app.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    val movies = MutableLiveData<List<Movie>>()

    fun getMovies(){
        viewModelScope.launch(Dispatchers.Default){
            movies.postValue(MovieRepository.getMovies())
        }
    }

//    fun getMovieActors(movie_id: Int): List<Actor>{
//        viewModelScope.launch(Dispatchers.Default){
//            return@launch MovieRepository.getMovieCast(movie_id)
//        }
//    }
}