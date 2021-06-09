package com.example.retrofitroom.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.retrofitroom.R
import com.example.retrofitroom.data.db.cache.MoviesDataBase
import com.example.retrofitroom.data.db.remote.api.RetrofitInstance
import com.example.retrofitroom.data.db.repository.MoviesRepositoryImpl
import com.example.retrofitroom.data.db.repository.dataImpl.MoviesLocalDataImpl
import com.example.retrofitroom.data.db.repository.dataImpl.MoviesRemoteDataImpl
import com.example.retrofitroom.data.db.repository.dataSource.MoviesLocalDataSource
import com.example.retrofitroom.data.db.repository.dataSource.MoviesRemoteDataSource
import com.example.retrofitroom.databinding.FragmentMoviesBinding
import com.example.retrofitroom.domain.interactor.usecase.DeleteSavedMoviesUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetMoviesUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetSavedMoviesUseCase
import com.example.retrofitroom.domain.interactor.usecase.SaveMoviesUseCase
import com.example.retrofitroom.domain.repository.MoviesRepository
import com.example.retrofitroom.presentation.MoviesViewModel
import com.example.retrofitroom.presentation.MoviesViewModelProviderFactory
import com.example.retrofitroom.presentation.adapter.MoviesAdapter

class MoviesFragment : Fragment(R.layout.fragment_movies) {
    lateinit var viewModel: MoviesViewModel
    lateinit var moviesViewModelProviderFactory: MoviesViewModelProviderFactory
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var binding: FragmentMoviesBinding
    lateinit var getMoviesUseCase: GetMoviesUseCase
    lateinit var getSavedMoviesUseCase: GetSavedMoviesUseCase
    lateinit var saveMoviesUseCase: SaveMoviesUseCase
    lateinit var deleteSavedMoviesUseCase: DeleteSavedMoviesUseCase
    lateinit var moviesLocalDataSource: MoviesLocalDataSource
    lateinit var moviesRemoteDataSource: MoviesRemoteDataSource
    lateinit var moviesRepository: MoviesRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)
        val db = MoviesDataBase.invoke(view.context)

        moviesLocalDataSource = MoviesLocalDataImpl(db.getMovieDAO())
        moviesRemoteDataSource = MoviesRemoteDataImpl(RetrofitInstance.api)
        moviesRepository = MoviesRepositoryImpl(moviesLocalDataSource, moviesRemoteDataSource)

        deleteSavedMoviesUseCase = DeleteSavedMoviesUseCase(moviesRepository)
        getSavedMoviesUseCase = GetSavedMoviesUseCase(moviesRepository)
        saveMoviesUseCase = SaveMoviesUseCase(moviesRepository)
        getMoviesUseCase = GetMoviesUseCase(moviesRepository)

        moviesViewModelProviderFactory = MoviesViewModelProviderFactory(deleteSavedMoviesUseCase,getMoviesUseCase,getSavedMoviesUseCase,saveMoviesUseCase)
        viewModel = ViewModelProvider(this, moviesViewModelProviderFactory).get(MoviesViewModel::class.java)

        setupRecyclerView()

        moviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("result", it)
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
