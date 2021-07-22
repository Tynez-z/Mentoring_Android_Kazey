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
import com.example.retrofitroom.common.action
import com.example.retrofitroom.common.longSnackBar
import com.example.retrofitroom.databinding.FragmentSavedMoviesBinding
import com.example.retrofitroom.presentation.adapter.MoviesAdapter
import com.example.retrofitroom.presentation.viewmodel.SavedMoviesViewModel

class SavedMoviesFragment : BaseFragment(R.layout.fragment_saved_movies) {

    private lateinit var fragmentSavedMoviesBinding: FragmentSavedMoviesBinding
    private val moviesAdapter = MoviesAdapter()
    private val viewModel: SavedMoviesViewModel by lazy {
        viewModel {
            //TODO add logic in future (LifecycleOwner)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentSavedMoviesBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return fragmentSavedMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()
        showSavedMovies()
        showDescribeOfSavedMovie()
        setupSwipeToDeleteFunction()
    }

    private fun showSavedMovies() {
        viewModel.getSavedMovies().observe(viewLifecycleOwner, { movies ->
            moviesAdapter.differ.submitList(movies)
        })
    }

    private fun showDescribeOfSavedMovie() {
        moviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable(RESULT_NAV, it)
            }
            findNavController().navigate(R.id.action_savedMoviesFragment_to_articleFragment, bundle)
        }
    }

    private fun setupSwipeToDeleteFunction() {
        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean =
                true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                moviesAdapter.differ.currentList[viewHolder.adapterPosition].let { article ->
                    viewModel.deleteArticle(article)
                    view?.longSnackBar(R.string.movie_deleted) {
                        action(R.string.undo) {
                            viewModel.saveArticle(article)
                        }
                    }
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(fragmentSavedMoviesBinding.rvSaveMovies)
        }
    }

    private fun setupRecycleView() {
        fragmentSavedMoviesBinding.rvSaveMovies.adapter = moviesAdapter
    }
}