package com.app.movieapp.databinding

import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapp.adapters.CenterScaleLayoutManager
import com.app.movieapp.adapters.MoviesAdapter
import com.app.movieapp.models.Movie

@BindingAdapter("layoutMarginStart")
fun setLayoutMarginStart(view: View, dimen: Float) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.marginStart = dimen.toInt()
    view.layoutParams = layoutParams
}

@BindingAdapter("layoutMarginEnd")
fun setLayoutMarginEnd(view: View, dimen: Float) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.marginEnd = dimen.toInt()
    view.layoutParams = layoutParams
}

@BindingAdapter("moviesList")
fun setMoviesList(recyclerView: RecyclerView, movies: MutableList<Movie>) {
    val layoutManager = recyclerView.layoutManager
    if (layoutManager == null) {
        recyclerView.layoutManager =
            CenterScaleLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
    }
    var adapter = recyclerView.adapter
    if (adapter == null){
        adapter = MoviesAdapter(recyclerView.context, movies)
        recyclerView.adapter = adapter
    }
}