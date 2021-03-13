package com.exceptioncatchers.bookfinder.bookdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.loginregister.models.User
import com.exceptioncatchers.bookfinder.userlibrary.repository.BookLibraryRepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDetailsViewModel(
    private val libraryRepo: BookLibraryRepoInterface
) : ViewModel() {
    private val _bookDetailsResponse: MutableLiveData<BookDetails> = MutableLiveData()
    private val bookDetailsResponse: LiveData<BookDetails> get() = _bookDetailsResponse
    private val _user: MutableLiveData<User> = MutableLiveData()
    private val user: LiveData<User> get() = _user

    fun getBook(bookId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _bookDetailsResponse.postValue(libraryRepo.getBookDetails(bookId).value)
        }
    }

    fun getUser(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _user.postValue(libraryRepo.getUser(userId).value)
        }
    }

    fun getBookDetails(): LiveData<BookDetails> = bookDetailsResponse

    fun getUserInfo(): LiveData<User> = user
}