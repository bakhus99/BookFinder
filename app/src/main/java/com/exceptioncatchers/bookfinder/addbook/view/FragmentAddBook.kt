package com.exceptioncatchers.bookfinder.addbook.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.exceptioncatchers.bookfinder.R
import com.exceptioncatchers.bookfinder.addbook.viewmodel.AddBookViewModel
import com.exceptioncatchers.bookfinder.addbook.viewmodel.AddBookViewModelFactory
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.databinding.FragmentAddBookBinding

class FragmentAddBook : Fragment(R.layout.fragment_add_book) {
    private lateinit var binding: FragmentAddBookBinding
    private val addBookViewModel: AddBookViewModel by viewModels {
        AddBookViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBookBinding.bind(view)
        addBook()
    }

    private fun addBook() {
        val newBook = BookDetails(
            binding.addBookTitleEdtx.toString(),
            binding.addBookAuthorEdtx.toString(),
            binding.addBookPosterEdtx.toString(),
            binding.addBookDescriptionEdtx.toString(),
            0F, 0, "", ""
        )
        addBookViewModel.addBook(newBook).observe(this.viewLifecycleOwner, { responseMessage ->
            responseMessage?.let {
                Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        fun newInstance(): Fragment = FragmentAddBook()
    }
}