package com.exceptioncatchers.bookfinder.books_list.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.books_list.presentation.model.BookItem
import kotlinx.android.synthetic.main.book_item.view.*

class BooksListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val bookTitle: TextView = view.findViewById(R.id.book_item_title)
    private val bookAuthor: TextView = view.findViewById(R.id.book_item_author)
    private val bookImage: ImageView = view.findViewById(R.id.book_item_image)

    fun bind(bookItem: BookItem) {
        bookTitle.text = bookItem.title
        bookAuthor.text = bookItem.author
    }

}