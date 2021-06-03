package com.example.retrofitroom.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.retrofitroom.presentation.MovieViewModel
import com.example.retrofitroom.R
import com.example.retrofitroom.databinding.FragmentArticleBinding
import com.example.retrofitroom.fragments.ArticleFragmentArgs
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val viewModel: MovieViewModel by activityViewModels()
    private val args: ArticleFragmentArgs by navArgs()
    lateinit var binding: FragmentArticleBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentArticleBinding.bind(view)
        binding.apply {
            binding.setVariable(BR.movie, args.result)
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(args.result)
            Snackbar.make(requireView(), "Movie Saved Successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}





