package com.exceptioncatchers.bookfinder.books_list.presentation.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.books_list.presentation.model.BookItem

class BooksListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val booksList: MutableList<BookItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BooksListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.book_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BooksListViewHolder -> holder.bind(booksList[position])
            else -> Log.d("TAG", "error")
        }
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    fun setData(newData: List<BookItem>) {
        booksList.clear()
        booksList.addAll(newData)
        notifyDataSetChanged()
    }
}