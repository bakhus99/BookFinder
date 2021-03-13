package com.exceptioncatchers.bookfinder.books_list.presentation.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.databinding.BookItemBinding

class BooksListViewHolder(
    private val binding: BookItemBinding,
    private val action: OnBookClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(bookDetail: BookDetails) {
        binding.bookItemTitle.text = bookDetail.bookTitle
        binding.bookItemAuthor.text = bookDetail.bookAuthor
        Glide
            .with(binding.root)
            .load(bookDetail.bookPoster)
            .into(binding.bookItemImage)

        binding.root.setOnClickListener {
            action.onBookClick(bookDetail.bookId)
        }
    }
}

