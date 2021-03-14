package com.exceptioncatchers.bookfinder.addbook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.books_list.data.BooksListRepository
import com.exceptioncatchers.bookfinder.userlibrary.repository.BookLibraryRepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddBookViewModel(
    private val repository: BooksListRepository
) : ViewModel() {
    private val _message: MutableLiveData<String> = MutableLiveData()
    private val message: LiveData<String> get() = _message

    fun addBook(book: BookDetails): LiveData<String> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBookToFirebase(
                success = {
                    it?.let { _message.postValue(it) }
                },
                fail = { TODO() },
                bookDetails = book
            )
        }
        return message
    }
}