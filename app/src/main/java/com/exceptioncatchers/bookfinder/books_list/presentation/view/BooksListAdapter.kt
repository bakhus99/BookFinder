package com.exceptioncatchers.bookfinder.books_list.presentation.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.books_list.presentation.model.BookItem
import com.exceptioncatchers.bookfinder.databinding.BookItemBinding

class BooksListAdapter(val bookClickListener: OnBookClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val booksList: MutableList<BookDetails> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksListViewHolder(binding = binding, action = bookClickListener)
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

    fun setData(newData: List<BookDetails>) {
        booksList.clear()
        booksList.addAll(newData)
        notifyDataSetChanged()
    }
}