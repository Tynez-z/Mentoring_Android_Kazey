package com.example.retrofitroom.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.retrofitroom.presentation.MainActivity
import com.example.retrofitroom.presentation.MovieViewModel
import com.example.retrofitroom.R
import com.example.retrofitroom.Resource
import com.example.retrofitroom.adapter.MovieAdapter
import com.example.retrofitroom.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    lateinit var viewModel: MovieViewModel
    lateinit var movieAdapter: MovieAdapter
    lateinit var binding: FragmentMoviesBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        movieAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("result", it)
            }
            findNavController().navigate(R.id.action_moviesFragment_to_articleFragment, bundle)
        }

        viewModel.moviesNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { newsResponse ->
                        movieAdapter.differ.submitList(newsResponse.results)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e("TAG", "An error: $message")
                    }
                }
                is Resource.Loading -> {
                }
            }
        })
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvMovies.adapter = movieAdapter
    }
}
