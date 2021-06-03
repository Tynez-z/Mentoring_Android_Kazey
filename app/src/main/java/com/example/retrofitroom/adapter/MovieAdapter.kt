package com.example.retrofitroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitroom.R
import com.example.retrofitroom.databinding.FragmentItemArticlePreviewBinding
import com.example.retrofitroom.model.Result
import kotlinx.android.synthetic.main.fragment_item_article_preview.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ArticleViewHolder>() {

//    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class ArticleViewHolder(private val itemViewBinding: FragmentItemArticlePreviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bindView(movieItem: Result) {
            itemViewBinding.apply {
                movie = movieItem
            }

            itemViewBinding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(movieItem)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.fragment_item_article_preview, parent, false)
            )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val movieItem = differ.currentList[position]
        holder.bindView(movieItem)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }
}