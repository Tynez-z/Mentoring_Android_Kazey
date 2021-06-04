package com.example.retrofitroom.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.retrofitroom.BR
import com.example.retrofitroom.R
import com.example.retrofitroom.databinding.FragmentArticleBinding
import com.example.retrofitroom.presentation.MovieViewModel
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: MovieViewModel
    private val args: ArticleFragmentArgs by navArgs()
    lateinit var binding: FragmentArticleBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

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





