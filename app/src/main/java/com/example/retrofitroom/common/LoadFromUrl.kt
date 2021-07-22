package com.example.retrofitroom.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

//TODO it is better to name file BindingUtils

@BindingAdapter(POSTER)
fun loadFromUrl(view: ImageView, url: String?) {
    Glide.with(view)
        .load(URL_IMAGE + url)
        .into(view)
}