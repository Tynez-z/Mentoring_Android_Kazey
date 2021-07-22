package com.example.retrofitroom.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.retrofitroom.R
import com.example.retrofitroom.common.ERROR
import com.example.retrofitroom.common.RESULT_NAV
import com.example.retrofitroom.common.TAG
import com.example.retrofitroom.databinding.FragmentMoviesBinding
import com.example.retrofitroom.presentation.adapter.MoviesAdapter
import com.example.retrofitroom.presentation.viewmodel.MoviesViewModel

class MoviesFragment : BaseFragment(R.layout.fragment_movies) {

    lateinit var moviesAdapter: MoviesAdapter
    lateinit var fragmentMoviesBinding: FragmentMoviesBinding
    private val viewModel: MoviesViewModel by lazy {
        viewModel {
            //TODO add logic in future (LifecycleOwner)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMoviesBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getMovies()
        getMoviesErrorState()
        showDescribeMovie()
    }

    private fun getMoviesErrorState() {
        viewModel.errorStateLiveData.observe(viewLifecycleOwner, Observer { error ->
            Log.e(TAG, ERROR + error)
        })
    }

    private fun getMovies() {
        viewModel.moviesNews.observe(viewLifecycleOwner, Observer { response ->
            moviesAdapter.differ.submitList(response)
        })
    }

    private fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter()
        fragmentMoviesBinding.rvMovies.adapter = moviesAdapter
    }

    private fun showDescribeMovie() {
        moviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable(RESULT_NAV, it)
            }
            findNavController().navigate(R.id.action_moviesFragment_to_articleFragment, bundle)
        }
    }
}