package com.app.movieapp.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapp.R
import com.app.movieapp.adapters.CenterScaleLayoutManager
import com.app.movieapp.adapters.MoviesAdapter
import com.app.movieapp.databinding.FragmentHomeBinding
import com.app.movieapp.databinding.MovieDetailLayoutBinding
import com.app.movieapp.models.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomsheet.BottomSheetDialog

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w780"

class HomeFragment : Fragment() {
    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var bottomSheetBinding: MovieDetailLayoutBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater)
        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val bottomSheetView = inflater.inflate(R.layout.movie_detail_layout, null)
        bottomSheetBinding = MovieDetailLayoutBinding.bind(bottomSheetView)
        bottomSheetDialog.setContentView(bottomSheetView)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(mBinding.homeRecyclerView)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            mBinding.movies = movies
        })
        viewModel.actors.observe(viewLifecycleOwner, Observer { actors ->
            bottomSheetBinding.movie?.actors = actors
            bottomSheetDialog.show()
        })

        mBinding.homeRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val movie =
                    getCurrentlyVisibleMovie(recyclerView.layoutManager as LinearLayoutManager)
                if (movie != null) {
                    bottomSheetBinding.movie = movie
                    setScreenBackground(IMAGE_BASE_URL + movie.backdrop_path)
                }
            }
        })

        mBinding.button.setOnClickListener {
            val movie =
                getCurrentlyVisibleMovie(mBinding.homeRecyclerView.layoutManager as LinearLayoutManager)
            if (movie != null)
                viewModel.getMovieActors(movie.id)
        }
    }

    private fun getCurrentlyVisibleMovie(layoutManager: LinearLayoutManager): Movie? {
        val visiblePosition = layoutManager.findFirstCompletelyVisibleItemPosition()
        if (visiblePosition > -1) {
            return viewModel.movies.value?.get(visiblePosition)
        }
        return null
    }

    private fun setScreenBackground(url: String?) {
        Glide.with(requireContext())
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(mBinding.bgndImage)
    }
}