package com.app.movieapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapp.databinding.MovieListItemBinding
import com.app.movieapp.models.Movie

class MoviesAdapter(
    private val mContext: Context,
    private val mMoviesList: MutableList<Movie>
) : RecyclerView.Adapter<MoviesAdapter.BindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding = MovieListItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return BindingHolder(binding.root)
    }

    override fun getItemCount() = mMoviesList.size

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val movie = mMoviesList[position]
        holder.mBinding?.movie = movie
        holder.mBinding?.executePendingBindings()
    }

    inner class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mBinding = DataBindingUtil.bind<MovieListItemBinding>(itemView)
    }
}