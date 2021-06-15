package com.example.retrofitroom.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.retrofitroom.common.NAME_OF_DB
import com.example.retrofitroom.data.db.cache.MoviesDao
import com.example.retrofitroom.data.db.cache.MoviesDataBase
import com.example.retrofitroom.data.db.repository.dataImpl.MoviesLocalDataImpl
import com.example.retrofitroom.data.db.repository.dataSource.MoviesLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Singleton
    @Provides
    fun providesMovieLocalDataSource(dao: MoviesDao): MoviesLocalDataSource {
        return MoviesLocalDataImpl(dao)
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context): MoviesDataBase {
        return Room.databaseBuilder(context, MoviesDataBase::class.java, NAME_OF_DB).build()
    }

    @Singleton
    @Provides
    fun provideMoviesDao(db: MoviesDataBase): MoviesDao {
        return db.getMovieDAO()
    }
}