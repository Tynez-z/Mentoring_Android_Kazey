package com.example.retrofitroom.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.retrofitroom.R
import com.example.retrofitroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //lateinit var viewModel: MovieViewModel

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //val moviesRepository = MovieRepositoryImpl(MovieDataBase.invoke(this))
        //val viewModelProviderFactory = MovieViewModelProviderFactory(moviesRepository)
        //viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MovieViewModel::class.java)
        //viewModel.getBreakingNews()


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.NavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController((navController))


    }
}