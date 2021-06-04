package com.example.retrofitroom.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.retrofitroom.model.Result

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Result)

    @Query("SELECT * FROM movies")
    fun getAllMovie(): LiveData<List<Result>>

    @Delete
    suspend fun deleteMovie (movie: Result)
}
