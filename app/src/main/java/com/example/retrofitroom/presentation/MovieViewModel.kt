package com.example.retrofitroom.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitroom.Resource
import com.example.retrofitroom.data.repository.MovieRepositoryImpl
import com.example.retrofitroom.domain.interactor.usecase.GetMovieUseCase
import com.example.retrofitroom.model.MoviesResponse
import com.example.retrofitroom.model.Result
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel (val moviesRepository: MovieRepositoryImpl,
                      val getNewsUseCase : GetMovieUseCase) : ViewModel() {

    val moviesNews: MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()

    init {
        getBreakingNews()
    }

//    fun getBreakingNews() = viewModelScope.launch {
//        moviesNews.postValue(Resource.Loading())
//        val response = moviesRepository.getMovie()
//        moviesNews.postValue(handleBreakingNewsResponse(response))
//    }

    fun getBreakingNews() = viewModelScope.launch {
        moviesNews.postValue(Resource.Loading())
        val response = getNewsUseCase.getMovieFromUseCase()
        moviesNews.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<MoviesResponse>): Resource<MoviesResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    fun saveArticle(movie:Result) = viewModelScope.launch {
        moviesRepository.upsert(movie)
    }

    fun getSavedMovies() = moviesRepository.getSavedMovie()


    fun deleteArticle(movie: Result) = viewModelScope.launch {
        moviesRepository.upsert(movie)
    }

}