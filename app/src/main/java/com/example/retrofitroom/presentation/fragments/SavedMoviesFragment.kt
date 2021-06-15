package com.example.retrofitroom.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitroom.R
import com.example.retrofitroom.common.RESULT_NAV
import com.example.retrofitroom.databinding.FragmentSavedMoviesBinding
import com.example.retrofitroom.presentation.App
import com.example.retrofitroom.presentation.MoviesViewModel
import com.example.retrofitroom.presentation.adapter.MoviesAdapter
import com.google.android.material.snackbar.Snackbar

class SavedMoviesFragment : BaseFragment(R.layout.fragment_saved_movies) {
    lateinit var binding: FragmentSavedMoviesBinding
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var viewModel: MoviesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedMoviesBinding.bind(view)
        viewModel = viewModel { viewModelProvider }
        setupRecycleView()

        viewModel.getSavedMovies().observe(viewLifecycleOwner, { movies ->
            moviesAdapter.differ.submitList(movies)
        })

        moviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable(RESULT_NAV, it)
            }
            findNavController().navigate(R.id.action_savedMoviesFragment_to_articleFragment, bundle)
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
                    setAction(R.string.undo) {
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