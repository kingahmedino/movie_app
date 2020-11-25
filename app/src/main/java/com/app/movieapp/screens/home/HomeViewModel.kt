package com.app.movieapp.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.movieapp.R
import com.app.movieapp.models.Actor
import com.app.movieapp.models.Movie

class HomeViewModel: ViewModel() {
    val movies = MutableLiveData<MutableList<Movie>>()
    var actors = mutableListOf<Actor>()

    private fun setUpActorsList(){
        actors.add(Actor("Joaquin Joker", null, R.drawable.joaquin))
        actors.add(Actor("Will Smith", null, R.drawable.will))
        actors.add(Actor("Zazie Beetz", null, R.drawable.zazie))
        actors.add(Actor("Robert De Niro", null, R.drawable.roberto))
    }

    fun setUpMoviesList(){
        setUpActorsList()
        val moviesList = mutableListOf<Movie>()
        moviesList.add(Movie("Joker", null, R.drawable.joker_1, actors))
        moviesList.add(Movie("The Hustle", null, R.drawable.hustle_2, actors))
        moviesList.add(Movie("Bad Boys for Life", null, R.drawable.badboys_1, actors))
        moviesList.add(Movie("The Old Guard", null, R.drawable.old_guard_2, actors))
        moviesList.add(Movie("Sonic The Hedgehog", null, R.drawable.sonic_1, actors))

        movies.value = moviesList
    }
}