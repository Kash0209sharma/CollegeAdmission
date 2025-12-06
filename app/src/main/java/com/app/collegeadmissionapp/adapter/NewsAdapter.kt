package com.app.collegeadmissionapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.collegeadmissionapp.data.NewsItem
import com.app.collegeadmissionapp.databinding.ItemNewsBinding

class NewsAdapter(private val newsList: List<NewsItem>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    // 2. Modify the ViewHolder to hold the binding object instead of individual Views
    inner class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        // Views are now accessed directly via the 'binding' object,
        // so no need for explicit declarations or findViewById calls here.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        // 3. Inflate the layout using the binding class
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false // Attach to root is false for RecyclerView adapters
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]

        // 4. Access the views via the holder's binding object
        holder.binding.newsTitle.text = newsItem.title
        holder.binding.newsDescription.text = newsItem.description
        holder.binding.newsTime.text = newsItem.time
    }

    override fun getItemCount() = newsList.size
}