package com.example.retrofitroom.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitroom.presentation.MoviesViewModelProviderFactory
import com.example.retrofitroom.presentation.ViewModelKey
import com.example.retrofitroom.presentation.viewmodel.ArticleViewModel
import com.example.retrofitroom.presentation.viewmodel.MoviesViewModel
import com.example.retrofitroom.presentation.viewmodel.SavedMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: MoviesViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    abstract fun bindArticleViewModel(viewModel: ArticleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SavedMoviesViewModel::class)
    abstract fun bindSavedMoviesViewModel(viewModel: SavedMoviesViewModel): ViewModel
}