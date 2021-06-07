package com.example.retrofitroom.data.db.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitroom.model.Result

@Database(entities = [Result::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun getMovieDAO(): MovieDao

    companion object
    {
        @Volatile
        private  var instance : MovieDataBase? = null
        private  val Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock){
            instance ?: createDatabase(context).also { instance = it }
        }

        private  fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDataBase::class.java,
                "article_db.db"
            ).build()
    }
}