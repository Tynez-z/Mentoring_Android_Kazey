package com.example.retrofitroom.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitroom.R
import com.example.retrofitroom.data.db.cache.MovieDataBase
import com.example.retrofitroom.data.db.remote.api.RetrofitInstance
import com.example.retrofitroom.data.db.repository.MovieRepositoryImpl
import com.example.retrofitroom.data.db.repository.dataImpl.MovieLocalDataImpl
import com.example.retrofitroom.data.db.repository.dataImpl.MovieRemoteDataImpl
import com.example.retrofitroom.data.db.repository.dataSource.MovieLocalDataSource
import com.example.retrofitroom.data.db.repository.dataSource.MovieRemoteDataSource
import com.example.retrofitroom.databinding.FragmentSavedMoviesBinding
import com.example.retrofitroom.domain.interactor.usecase.DeleateSavedMovieUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetMovieUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetSavedMovieUseCase
import com.example.retrofitroom.domain.interactor.usecase.SaveMovieUseCase
import com.example.retrofitroom.domain.repository.MovieRepository
import com.example.retrofitroom.presentation.MovieViewModel
import com.example.retrofitroom.presentation.MovieViewModelProviderFactory
import com.example.retrofitroom.presentation.adapter.MovieAdapter
import com.google.android.material.snackbar.Snackbar

class SavedMoviesFragment : Fragment(R.layout.fragment_saved_movies) {
    lateinit var binding: FragmentSavedMoviesBinding
    lateinit var moviesAdapter: MovieAdapter
    lateinit var viewModel: MovieViewModel
    lateinit var movieViewModelProviderFactory: MovieViewModelProviderFactory
    lateinit var getMovieUseCase: GetMovieUseCase
    lateinit var getSavedMovieUseCase: GetSavedMovieUseCase
    lateinit var saveMovieUseCase: SaveMovieUseCase
    lateinit var deleateSavedMovieUseCase: DeleateSavedMovieUseCase
    lateinit var movieLocalDataSource: MovieLocalDataSource
    lateinit var movieRemoteDataSource: MovieRemoteDataSource
    lateinit var movieRepository: MovieRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedMoviesBinding.bind(view)
        val db = MovieDataBase.invoke(view.context)
        movieLocalDataSource = MovieLocalDataImpl(db.getMovieDAO())
        movieRemoteDataSource = MovieRemoteDataImpl(RetrofitInstance.api)
        movieRepository = MovieRepositoryImpl(movieLocalDataSource, movieRemoteDataSource)
        deleateSavedMovieUseCase = DeleateSavedMovieUseCase(movieRepository)
        getSavedMovieUseCase = GetSavedMovieUseCase(movieRepository)
        saveMovieUseCase = SaveMovieUseCase(movieRepository)
        getMovieUseCase = GetMovieUseCase(movieRepository)
        movieViewModelProviderFactory = MovieViewModelProviderFactory(deleateSavedMovieUseCase,getMovieUseCase,getSavedMovieUseCase,saveMovieUseCase)
        viewModel = ViewModelProvider(this, movieViewModelProviderFactory).get(MovieViewModel::class.java)

        setupRecycleView()
        viewModel.getSavedMovies().observe(viewLifecycleOwner, { movies ->
            moviesAdapter.differ.submitList(movies) })

        moviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("result",it)
            }
            findNavController().navigate(R.id.action_savedMoviesFragment_to_articleFragment,bundle)
        }
        setupSwipeToDeleteFunction()
    }

    private fun setupSwipeToDeleteFunction() {
        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = moviesAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)

                Snackbar.make(requireView(), "Successfully deleted ", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveArticle(article)
                    }
                }.show()
            }
        }

        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(binding.recyclerView)
        }
    }

    private fun setupRecycleView() {
        moviesAdapter = MovieAdapter()
        binding.recyclerView.adapter = moviesAdapter
    }
}