package com.example.retrofitroom.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.retrofitroom.BR
import com.example.retrofitroom.R
import com.example.retrofitroom.databinding.FragmentArticleBinding
import com.example.retrofitroom.presentation.App
import com.example.retrofitroom.presentation.MoviesViewModel
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : BaseFragment(R.layout.fragment_article) {

    private val args: ArticleFragmentArgs by navArgs()
    lateinit var binding: FragmentArticleBinding
    lateinit var viewModel: MoviesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)

        viewModel = viewModel { viewModelProvider }

        binding.apply {
            binding.setVariable(BR.movie, args.result)
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(args.result)
            Snackbar.make(requireView(), R.string.movie_saved, Snackbar.LENGTH_SHORT).show()
        }
    }
}