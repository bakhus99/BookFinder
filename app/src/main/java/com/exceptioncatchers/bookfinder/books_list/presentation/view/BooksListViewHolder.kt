package com.exceptioncatchers.bookfinder.books_list.presentation.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exceptioncatchers.bookfinder.books_list.presentation.model.BookItem
import com.exceptioncatchers.bookfinder.databinding.BookItemBinding

class BooksListViewHolder(private val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(bookItem: BookItem) {
        binding.bookItemTitle.text = bookItem.title
        binding.bookItemAuthor.text = bookItem.author
        Glide
            .with(binding.root)
            .load("https://i.picsum.photos/id/1/5616/3744.jpg?hmac=kKHwwU8s46oNettHKwJ24qOlIAsWN9d2TtsXDoCWWsQ")
            .into(binding.bookItemImage)
    }
}