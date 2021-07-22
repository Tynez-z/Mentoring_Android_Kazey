package com.example.retrofitroom.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.retrofitroom.R
import com.example.retrofitroom.databinding.FragmentArticleBinding
import com.example.retrofitroom.presentation.viewmodel.ArticleViewModel

class ArticleFragment : BaseFragment(R.layout.fragment_article) {

    private val args: ArticleFragmentArgs by navArgs()
    private lateinit var fragmentArticleBinding: FragmentArticleBinding

    private val viewModel: ArticleViewModel by lazy {
        viewModel {
            //TODO add logic in future (LifecycleOwner)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentArticleBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return fragmentArticleBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveMovies()
    }

    fun saveMovies() {
        args.result.let { result ->
            fragmentArticleBinding.movie = result
            viewModel.saveArticle(result)
        }
    }
}