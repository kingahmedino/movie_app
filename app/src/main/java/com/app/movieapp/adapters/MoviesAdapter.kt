package com.app.movieapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapp.databinding.MovieListItemBinding
import com.app.movieapp.models.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class MoviesAdapter(
    private val mContext: Context,
    private val mMoviesList: List<Movie>
) : RecyclerView.Adapter<MoviesAdapter.BindingHolder>() {
    private val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w300"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val binding = MovieListItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return BindingHolder(binding.root)
    }

    override fun getItemCount() = mMoviesList.size

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val movie = mMoviesList[position]
        if (position == 0)
            holder.mBinding!!.isFirstItem = true
        else if (position == mMoviesList.size - 1)
            holder.mBinding!!.isLastItem = true
        holder.mBinding?.movie = movie
        Glide.with(mContext)
            .load(IMAGE_BASE_URL + movie.poster_path)
            .placeholder(movie.placeHolder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.mBinding?.movieImageView)

        holder.mBinding?.executePendingBindings()
    }

    inner class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mBinding = DataBindingUtil.bind<MovieListItemBinding>(itemView)
    }
}