package com.example.retrofitroom.presentation.di

import android.content.Context
import com.example.retrofitroom.data.db.repository.MoviesRepositoryImpl
import com.example.retrofitroom.data.db.repository.dataSource.MoviesLocalDataSource
import com.example.retrofitroom.data.db.repository.dataSource.MoviesRemoteDataSource
import com.example.retrofitroom.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideAppContext(): Context = context

    @Singleton
    @Provides
    fun provideRepository(moviesRemoteDataSource: MoviesRemoteDataSource,
        moviesLocalDataSource: MoviesLocalDataSource): MoviesRepository {
        return MoviesRepositoryImpl(moviesLocalDataSource, moviesRemoteDataSource)
    }
}