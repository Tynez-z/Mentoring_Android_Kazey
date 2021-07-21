package com.example.retrofitroom.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitroom.R
import com.example.retrofitroom.common.RESULT_NAV
import com.example.retrofitroom.databinding.FragmentSavedMoviesBinding
import com.example.retrofitroom.presentation.adapter.MoviesAdapter
import com.example.retrofitroom.presentation.viewmodel.SavedMoviesViewModel
import com.google.android.material.snackbar.Snackbar

class SavedMoviesFragment : BaseFragment(R.layout.fragment_saved_movies) {

    lateinit var fragmentSavedMoviesBinding: FragmentSavedMoviesBinding
    lateinit var moviesAdapter: MoviesAdapter
    private val viewModel: SavedMoviesViewModel by lazy {
        viewModel {
            //TODO add logic in future (LifecycleOwner)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSavedMoviesBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return fragmentSavedMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()

        //TODO use single fun
        viewModel.getSavedMovies().observe(viewLifecycleOwner, { movies ->
            moviesAdapter.differ.submitList(movies)
        })

        //TODO use single fun
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

                //TODO use extension
                Snackbar.make(requireView(), R.string.movie_deleted, Snackbar.LENGTH_LONG).apply {
                    setAction(R.string.undo) {
                        viewModel.saveArticle(article)
                    }
                }.show()
            }
        }

        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(fragmentSavedMoviesBinding.rvSaveMovies)
        }
    }

    private fun setupRecycleView() {
        moviesAdapter = MoviesAdapter()
        fragmentSavedMoviesBinding.rvSaveMovies.adapter = moviesAdapter
    }
}