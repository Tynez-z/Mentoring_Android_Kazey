package com.example.retrofitroom.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitroom.Resource
import com.example.retrofitroom.domain.interactor.usecase.DeleateSavedMovieUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetMovieUseCase
import com.example.retrofitroom.domain.interactor.usecase.GetSavedMovieUseCase
import com.example.retrofitroom.domain.interactor.usecase.SaveMovieUseCase
import com.example.retrofitroom.model.MoviesResponse
import com.example.retrofitroom.model.Result
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    private val deleateSavedMovieUseCase: DeleateSavedMovieUseCase,
    private val getSavedNewsUseCase: GetSavedMovieUseCase,
    private val saveMovieUseCase: SaveMovieUseCase
) : ViewModel() {

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
        val response = getMovieUseCase.execute()
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
        saveMovieUseCase.execute(movie)
    }

    fun getSavedMovies() = getSavedNewsUseCase.execute()

    fun deleteArticle(movie: Result) = viewModelScope.launch {
        deleateSavedMovieUseCase.execute(movie)
    }
}