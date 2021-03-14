package com.exceptioncatchers.bookfinder.addbook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.userlibrary.repository.BookLibraryRepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddBookViewModel(
    private val bookLibraryRepoInterface: BookLibraryRepoInterface
) : ViewModel() {

    fun addBook(book: BookDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            bookLibraryRepoInterface.addNewBook(book)
        }
    }
}