package com.exceptioncatchers.bookfinder.books_list.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.books_list.presentation.model.BookItem
import com.exceptioncatchers.bookfinder.books_list.presentation.view_model.BooksListViewModel
import com.exceptioncatchers.bookfinder.books_list.presentation.view_model.BooksListViewModelFactory
import com.exceptioncatchers.bookfinder.databinding.FragmentBooksListBinding

class BooksListFragment : Fragment(R.layout.fragment_books_list), OnBookClickListener {

    private lateinit var bindingBooksList: FragmentBooksListBinding
    private val adapter = BooksListAdapter(this)

    private val booksListViewModel: BooksListViewModel by viewModels{
        BooksListViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingBooksList = FragmentBooksListBinding.bind(view)

        setupListeners()
        initRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {
        booksListViewModel.loadBooksList()
        booksListViewModel.getTestData().observe(this.viewLifecycleOwner, ::handleBooksList)
    }

    private fun handleBooksList(booksList: List<BookDetails>?) {
        Log.d("TAG", "******************* ${booksList?.get(0)?.bookTitle}")
        Log.d("TAG", "******************* ${booksList?.get(1)?.bookTitle}")
        Log.d("TAG", "******************* ${booksList?.get(2)?.bookTitle}")
        adapter.setData(booksList!!)
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
        bindingBooksList.booksListRecyclerView.hasFixedSize()
        bindingBooksList.booksListRecyclerView.layoutManager = LinearLayoutManager(context)
        bindingBooksList.booksListRecyclerView.adapter = adapter
    }

    private fun setupListeners() {

    }

    override fun onBookClick(bookId: String) {
        Toast.makeText(requireContext(), "****** $bookId *****", Toast.LENGTH_SHORT).show()
//        val action = BooksListFragmentDirections.actionBooksListFragmentToFragmentBookDetails()
//        findNavController().navigate(action)
    }
}

