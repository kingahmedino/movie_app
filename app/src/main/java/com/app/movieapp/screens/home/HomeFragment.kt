package com.app.movieapp.screens.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
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
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment() {
    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var bottomSheetBinding: MovieDetailLayoutBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getMovies()
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
            mBinding.homeRecyclerView.also {
                it.setHasFixedSize(true)
                it.layoutManager = CenterScaleLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                it.adapter = MoviesAdapter(requireContext(), movies)
            }
        })

        mBinding.homeRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                changeBackgroundImage(recyclerView.layoutManager as LinearLayoutManager)
            }
        })

        mBinding.button.setOnClickListener {
            bottomSheetDialog.show()
        }
    }

    private fun changeBackgroundImage(layoutManager: LinearLayoutManager) {
        val visiblePosition = layoutManager.findFirstCompletelyVisibleItemPosition()
        if (visiblePosition > -1){
            val currentView = layoutManager.findViewByPosition(visiblePosition)
            val drawable = currentView!!.findViewById<ImageView>(R.id.movie_image_view).drawable
            mBinding.bgndImage.setImageDrawable(drawable)
            bottomSheetBinding.movie = viewModel.movies.value?.get(visiblePosition)
        }
    }
}