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
import com.example.retrofitroom.data.db.repository.MoviesRepositoryImpl
import com.example.retrofitroom.data.db.repository.dataImpl.MoviesLocalDataImpl
import com.example.retrofitroom.data.db.repository.dataImpl.MoviesRemoteDataImpl
import com.example.retrofitroom.data.db.repository.dataSource.MoviesLocalDataSource
import com.example.retrofitroom.data.db.repository.dataSource.MoviesRemoteDataSource
import com.example.retrofitroom.databinding.FragmentSavedMoviesBinding
import com.example.retrofitroom.domain.interactor.usecase.DeleteSavedMoviesUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetMoviesUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetSavedMoviesUseCase
import com.example.retrofitroom.domain.interactor.usecase.SaveMoviesUseCase
import com.example.retrofitroom.domain.repository.MoviesRepository
import com.example.retrofitroom.presentation.MoviesViewModel
import com.example.retrofitroom.presentation.MoviesViewModelProviderFactory
import com.example.retrofitroom.presentation.adapter.MoviesAdapter
import com.google.android.material.snackbar.Snackbar

//TODO will update after learning dagger 2 (UseCase in ViewModel)
class SavedMoviesFragment : Fragment(R.layout.fragment_saved_movies) {
    lateinit var binding: FragmentSavedMoviesBinding
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var viewModel: MoviesViewModel
    lateinit var moviesViewModelProviderFactory: MoviesViewModelProviderFactory
    lateinit var getMoviesUseCase: GetMoviesUseCase
    lateinit var getSavedMoviesUseCase: GetSavedMoviesUseCase
    lateinit var saveMoviesUseCase: SaveMoviesUseCase
    lateinit var deleteSavedMoviesUseCase: DeleteSavedMoviesUseCase
    lateinit var moviesLocalDataSource: MoviesLocalDataSource
    lateinit var moviesRemoteDataSource: MoviesRemoteDataSource
    lateinit var moviesRepository: MoviesRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedMoviesBinding.bind(view)
        val db = MovieDataBase.invoke(view.context)

        moviesLocalDataSource = MoviesLocalDataImpl(db.getMovieDAO())
        moviesRemoteDataSource = MoviesRemoteDataImpl(RetrofitInstance.api)
        moviesRepository = MoviesRepositoryImpl(moviesLocalDataSource, moviesRemoteDataSource)

        deleteSavedMoviesUseCase = DeleteSavedMoviesUseCase(moviesRepository)
        getSavedMoviesUseCase = GetSavedMoviesUseCase(moviesRepository)
        saveMoviesUseCase = SaveMoviesUseCase(moviesRepository)
        getMoviesUseCase = GetMoviesUseCase(moviesRepository)

        moviesViewModelProviderFactory = MoviesViewModelProviderFactory(deleteSavedMoviesUseCase,getMoviesUseCase,getSavedMoviesUseCase,saveMoviesUseCase)
        viewModel = ViewModelProvider(this, moviesViewModelProviderFactory).get(MoviesViewModel::class.java)

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

                Snackbar.make(requireView(), R.string.movie_deleted, Snackbar.LENGTH_LONG).apply {
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
        moviesAdapter = MoviesAdapter()
        binding.recyclerView.adapter = moviesAdapter
    }
}