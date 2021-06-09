package com.example.retrofitroom.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.retrofitroom.BR
import com.example.retrofitroom.R
import com.example.retrofitroom.data.db.cache.MovieDataBase
import com.example.retrofitroom.data.db.remote.api.RetrofitInstance
import com.example.retrofitroom.data.db.repository.MovieRepositoryImpl
import com.example.retrofitroom.data.db.repository.dataImpl.MovieLocalDataImpl
import com.example.retrofitroom.data.db.repository.dataImpl.MovieRemoteDataImpl
import com.example.retrofitroom.data.db.repository.dataSource.MovieLocalDataSource
import com.example.retrofitroom.data.db.repository.dataSource.MovieRemoteDataSource
import com.example.retrofitroom.databinding.FragmentArticleBinding
import com.example.retrofitroom.domain.interactor.usecase.DeleateSavedMovieUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetMovieUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetSavedMovieUseCase
import com.example.retrofitroom.domain.interactor.usecase.SaveMovieUseCase
import com.example.retrofitroom.domain.repository.MovieRepository
import com.example.retrofitroom.presentation.MovieViewModel
import com.example.retrofitroom.presentation.MovieViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val args: ArticleFragmentArgs by navArgs()
    lateinit var binding: FragmentArticleBinding
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
        binding = FragmentArticleBinding.bind(view)
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

        binding.apply {
            binding.setVariable(BR.movie, args.result) }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(args.result)
            //TODO use constants
            Snackbar.make(requireView(), "Movie Saved Successfully", Snackbar.LENGTH_SHORT).show() }
    }
}





