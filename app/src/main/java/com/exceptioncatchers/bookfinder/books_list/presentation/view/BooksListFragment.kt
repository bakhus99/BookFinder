package com.exceptioncatchers.bookfinder.books_list.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.books_list.presentation.model.BookItem
import com.exceptioncatchers.bookfinder.books_list.presentation.view_model.BooksListViewModel
import com.exceptioncatchers.bookfinder.books_list.presentation.view_model.BooksListViewModelFactory
import com.exceptioncatchers.bookfinder.databinding.FragmentBooksListBinding

class BooksListFragment : Fragment(R.layout.fragment_books_list) {

    private lateinit var binding: FragmentBooksListBinding
    private val adapter = BooksListAdapter()

    private val booksListViewModel: BooksListViewModel by viewModels{
        BooksListViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBooksListBinding.bind(view)

        setupListeners()
        initRecyclerView()
    }

    private fun mockData(): List<BookItem>{
        return listOf(
            BookItem("AAAAA", "ssss", "sasasas"),
            BookItem("AAAAA", "ssss", "sasasas"),
            BookItem("AAAAA", "ssss", "sasasas"),
            BookItem("AAAAA", "ssss", "sasasas"),
            BookItem("AAAAA", "ssss", "sasasas"),
        )
    }

    private fun initRecyclerView() {
        binding.booksListRecyclerView.hasFixedSize()
        binding.booksListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.booksListRecyclerView.adapter = adapter
        adapter.setData(mockData())
    }

    private fun setupListeners() {

    }
}

