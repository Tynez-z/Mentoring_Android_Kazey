package com.example.retrofitroom.data.db.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitroom.domain.entity.Result

@Database(entities = [Result::class], version = 1)
abstract class MoviesDataBase : RoomDatabase() {

    abstract fun getMovieDAO(): MoviesDao

    companion object {
        @Volatile
        private  var instance : MoviesDataBase? = null
        private  val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also { instance = it }
        }

        private  fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MoviesDataBase::class.java,
                "article_db.db"
            ).build()
    }
}