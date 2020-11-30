package com.app.movieapp.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.movieapp.models.Actor
import com.app.movieapp.models.Movie

class HomeViewModel: ViewModel() {
    val movies = MutableLiveData<MutableList<Movie>>()
    var actors = mutableListOf<Actor>()
}