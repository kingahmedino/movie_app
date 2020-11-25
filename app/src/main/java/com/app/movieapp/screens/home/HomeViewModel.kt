package com.app.movieapp.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.movieapp.R
import com.app.movieapp.models.Movie

class HomeViewModel: ViewModel() {
    val movies = MutableLiveData<MutableList<Movie>>()

    fun setUpMoviesList(){
        val moviesList = mutableListOf<Movie>()
        moviesList.add(Movie("Joker", null, R.drawable.joker_1, null))
        moviesList.add(Movie("The Hustle", null, R.drawable.hustle_2, null))
        moviesList.add(Movie("Bad Boys for Life", null, R.drawable.badboys_1, null))
        moviesList.add(Movie("The Old Guard", null, R.drawable.old_guard_2, null))
        moviesList.add(Movie("Sonic The Hedgehog", null, R.drawable.sonic_1, null))

        movies.value = moviesList
    }
}