package com.example.retrofitroom.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitroom.R
import com.example.retrofitroom.databinding.FragmentSavedMoviesBinding
import com.example.retrofitroom.presentation.MovieViewModel
import com.example.retrofitroom.presentation.adapter.MovieAdapter
import com.google.android.material.snackbar.Snackbar

class SavedMoviesFragment : Fragment(R.layout.fragment_saved_movies) {
    lateinit var binding: FragmentSavedMoviesBinding
    lateinit var moviesAdapter: MovieAdapter
    lateinit var viewModel: MovieViewModel



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedMoviesBinding.bind(view)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        setupRecycleView()


        viewModel.getSavedMovies().observe(viewLifecycleOwner, { movies ->
            moviesAdapter.differ.submitList(movies)
        })

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