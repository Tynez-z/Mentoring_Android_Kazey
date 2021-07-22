package com.example.retrofitroom.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitroom.common.ERROR
import com.example.retrofitroom.domain.entity.Result
import com.example.retrofitroom.domain.interactor.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    val moviesNews: MutableLiveData<List<Result>> = MutableLiveData()
    val errorStateLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        getBreakingNews()
    }

    private fun getBreakingNews() = viewModelScope.launch {
        val response = getMoviesUseCase.execute()
        if (!response.isSuccessful) {
            errorStateLiveData.postValue(ERROR)
            return@launch
        }
        moviesNews.postValue(response.body()!!.results)
    }
}