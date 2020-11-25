package com.app.movieapp.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.movieapp.R
import com.app.movieapp.models.Actor
import com.app.movieapp.models.Movie

class HomeViewModel: ViewModel() {
    val movies = MutableLiveData<MutableList<Movie>>()
    val actors = MutableLiveData<MutableList<Actor>>()

    fun setUpMoviesList(){
        val moviesList = mutableListOf<Movie>()
        moviesList.add(Movie("Joker", null, R.drawable.joker_1, null))
        moviesList.add(Movie("The Hustle", null, R.drawable.hustle_2, null))
        moviesList.add(Movie("Bad Boys for Life", null, R.drawable.badboys_1, null))
        moviesList.add(Movie("The Old Guard", null, R.drawable.old_guard_2, null))
        moviesList.add(Movie("Sonic The Hedgehog", null, R.drawable.sonic_1, null))

        movies.value = moviesList
    }

    fun setUpActorsList(){
        val actorsList = mutableListOf<Actor>()
        actorsList.add(Actor("Joaquin Joker", null, R.drawable.joaquin))
        actorsList.add(Actor("Will Smith", null, R.drawable.will))
        actorsList.add(Actor("Zazie Beetz", null, R.drawable.zazie))
        actorsList.add(Actor("Robert De Niro", null, R.drawable.roberto))

        actors.value = actorsList
    }
}