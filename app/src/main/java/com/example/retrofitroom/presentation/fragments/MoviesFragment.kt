package com.example.retrofitroom.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.retrofitroom.R
import com.example.retrofitroom.Resource
import com.example.retrofitroom.data.local.db.MovieDao
import com.example.retrofitroom.data.repository.MovieRepositoryImpl
import com.example.retrofitroom.data.repository.dataImpl.MovieLocalDataImpl
import com.example.retrofitroom.data.repository.dataSource.MovieLocalDataSource
import com.example.retrofitroom.data.repository.dataSource.MovieRemoteDataSource
import com.example.retrofitroom.databinding.FragmentMoviesBinding
import com.example.retrofitroom.domain.interactor.usecase.GetMovieUseCase
import com.example.retrofitroom.domain.repository.MovieRepository
import com.example.retrofitroom.presentation.MovieViewModel
import com.example.retrofitroom.presentation.MovieViewModelProviderFactory
import com.example.retrofitroom.presentation.adapter.MovieAdapter

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    lateinit var viewModel: MovieViewModel
    lateinit var movieViewModelProviderFactory: MovieViewModelProviderFactory
    lateinit var movieAdapter: MovieAdapter
    lateinit var binding: FragmentMoviesBinding
    lateinit var getMovieUseCase: GetMovieUseCase
    lateinit var movieLocalDataSource: MovieLocalDataSource
    lateinit var movieRemoteDataSource: MovieRemoteDataSource
    lateinit var movieRepository: MovieRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)
        viewModel = ViewModelProvider(this, movieViewModelProviderFactory()).get(MovieViewModel::class.java)
        movieLocalDataSource = MovieLocalDataImpl()
        movieRemoteDataSource = MovieLocalDataImpl()
        movieRepository = MovieRepositoryImpl()
        getMovieUseCase = GetMovieUseCase()

        // viewModel.getBreakingNews()
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
