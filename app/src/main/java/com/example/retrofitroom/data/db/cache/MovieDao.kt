package com.example.retrofitroom.data.db.cache

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.retrofitroom.model.Result

@Dao
interface MovieDao : MovieInterface {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(movie: Result)

    @Query("SELECT * FROM movies")
    override fun getAllMovie(): LiveData<List<Result>>

    @Delete
    override suspend fun deleteMovie(movie: Result)
}
