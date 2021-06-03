package com.example.retrofitroom.data.repository

import androidx.lifecycle.LiveData
import com.example.retrofitroom.data.local.db.MovieDataBase
import com.example.retrofitroom.data.remote.api.RetrofitInstance
import com.example.retrofitroom.model.MoviesResponse
import com.example.retrofitroom.model.Result
import retrofit2.Response

class MovieRepositoryImpl (private val db: MovieDataBase) : MovieRepositoryInterface {

    override suspend fun getMovie(): Response<MoviesResponse> {
        return RetrofitInstance.api.getMovie()
    }

    override suspend fun upsert(movie: Result): Long {
        return db.getMovieDAO().upsert(movie)
    }

    override suspend fun deleteMovie(movie: Result) {
        return db.getMovieDAO().deleteMovie(movie)
    }

    override fun getSavedMovie(): LiveData<List<Result>> {
        return db.getMovieDAO().getAllMovie()
    }
}