package com.example.retrofitroom.presentation.di

import com.example.retrofitroom.presentation.fragments.ArticleFragment
import com.example.retrofitroom.presentation.fragments.BaseFragment
import com.example.retrofitroom.presentation.fragments.MoviesFragment
import com.example.retrofitroom.presentation.fragments.SavedMoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, LocalModule::class, RemoteModule::class])
interface AppComponent {
    fun inject(moviesFragment: MoviesFragment)
    fun inject(articleFragment: ArticleFragment)
    fun inject(savedMoviesFragment: SavedMoviesFragment)
    fun inject(baseFragment: BaseFragment)
}