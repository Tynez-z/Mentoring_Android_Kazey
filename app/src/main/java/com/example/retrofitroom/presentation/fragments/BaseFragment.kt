package com.example.retrofitroom.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitroom.presentation.App
import javax.inject.Inject

abstract class BaseFragment(private val layoutId: Int) : Fragment(layoutId) {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProvider(this, viewModelProvider)[T::class.java]
        vm.body()
        return vm
    }
}