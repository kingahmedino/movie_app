package com.app.movieapp.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapp.R
import com.app.movieapp.adapters.CenterScaleLayoutManager
import com.app.movieapp.adapters.MoviesAdapter
import com.app.movieapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var mBinding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.setUpMoviesList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(mBinding.homeRecyclerView)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            mBinding.homeRecyclerView.also {
                it.layoutManager =
                    CenterScaleLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                it.setHasFixedSize(true)
                it.adapter = MoviesAdapter(requireContext(), movies)
                changeBackgroundImage(it.layoutManager as LinearLayoutManager)
            }
        })

        mBinding.homeRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                changeBackgroundImage(recyclerView.layoutManager as LinearLayoutManager)
            }
        })
    }

    private fun changeBackgroundImage(layoutManager: LinearLayoutManager) {
        val visiblePosition = layoutManager.findFirstCompletelyVisibleItemPosition()
        if (visiblePosition > -1){
            val currentView = layoutManager.findViewByPosition(visiblePosition)
            val drawable = currentView!!.findViewById<ImageView>(R.id.movie_image_view).drawable
            mBinding.bgndImage.setImageDrawable(drawable)
        }
    }
}