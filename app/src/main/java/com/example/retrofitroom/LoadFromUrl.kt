package com.example.retrofitroom

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_item_article_preview.view.*

    @BindingAdapter("poster")
    fun loadFromUrl(view: ImageView, url: String?) {
        Glide.with(view)
            .load("https://image.tmdb.org/t/p/original/$url").into(view)
    }


