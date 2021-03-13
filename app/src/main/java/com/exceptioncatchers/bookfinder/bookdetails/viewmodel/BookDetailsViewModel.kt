package com.exceptioncatchers.bookfinder.bookdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.books_list.data.BooksListRepository
import com.exceptioncatchers.bookfinder.loginregister.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDetailsViewModel(
    private val libraryRepo: BooksListRepository
) : ViewModel() {
    private val _bookDetailsResponse: MutableLiveData<BookDetails> = MutableLiveData()
    private val bookDetailsResponse: LiveData<BookDetails> get() = _bookDetailsResponse
    private val _user: MutableLiveData<User> = MutableLiveData()
    private val user: LiveData<User> get() = _user

    fun getBook(bookId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            libraryRepo.getBookDetails(
                success = {
                    it?.let { _bookDetailsResponse.postValue(it) }
                },
                fail = { TODO() },
                bookId = bookId
            )
        }
    }

    fun getUser(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            libraryRepo.getUserById(
                success = {
                    it?.let { _user.postValue(it) }
                },
                fail = { TODO() },
                userId = userId
            )
        }
    }

    fun getBookDetails(): LiveData<BookDetails> = bookDetailsResponse

    fun getUserInfo(): LiveData<User> = user
}