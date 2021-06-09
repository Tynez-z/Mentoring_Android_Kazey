package com.example.retrofitroom.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(POSTER)
    fun loadFromUrl(view: ImageView, url: String?) {
        Glide.with(view)
            .load("https://image.tmdb.org/t/p/original/$url").into(view) //TODO put to the Constants
    }