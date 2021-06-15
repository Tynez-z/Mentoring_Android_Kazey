package com.example.retrofitroom.data.db.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.retrofitroom.domain.entity.Result

@Database(entities = [Result::class], version = 1)
abstract class MoviesDataBase : RoomDatabase() {
    abstract fun getMovieDAO(): MoviesDao
}