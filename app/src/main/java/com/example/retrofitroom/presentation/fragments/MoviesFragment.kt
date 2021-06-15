package com.example.retrofitroom.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.retrofitroom.R
import com.example.retrofitroom.common.RESULT_NAV
import com.example.retrofitroom.databinding.FragmentMoviesBinding
import com.example.retrofitroom.presentation.App
import com.example.retrofitroom.presentation.MoviesViewModel
import com.example.retrofitroom.presentation.adapter.MoviesAdapter

class MoviesFragment() : BaseFragment(R.layout.fragment_movies) {

    lateinit var viewModel: MoviesViewModel
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var binding: FragmentMoviesBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)

        viewModel = viewModel { viewModelProvider }
        setupRecyclerView()

        moviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable(RESULT_NAV, it)
            }
            findNavController().navigate(R.id.action_moviesFragment_to_articleFragment, bundle)
        }

        viewModel.moviesNews.observe(viewLifecycleOwner, Observer { response ->
            moviesAdapter.differ.submitList(response.results)
        })
        viewModel.errorStateLiveData.observe(viewLifecycleOwner, Observer { error ->
            Log.e("TAG", "Error: $error")
        })
    }
    
        private fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter()
        binding.rvMovies.adapter = moviesAdapter
    }
}