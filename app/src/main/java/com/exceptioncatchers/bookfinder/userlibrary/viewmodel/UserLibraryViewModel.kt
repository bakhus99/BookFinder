package com.exceptioncatchers.bookfinder.userlibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.books_list.data.BooksListRepository
import com.exceptioncatchers.bookfinder.loginregister.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserLibraryViewModel(
    private val libraryRepo: BooksListRepository
) : ViewModel() {
    private val _bookListResponse: MutableLiveData<List<BookDetails>> = MutableLiveData()
    private val bookListResponse: LiveData<List<BookDetails>> get() = _bookListResponse
    private val _userInfo: MutableLiveData<User> = MutableLiveData()
    private val userInfo: LiveData<User> get() = _userInfo

    fun getUser(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            libraryRepo.getUserById(
                success = {
                    it?.let { _userInfo.postValue(it) }
                },
                fail = { TODO() },
                userId = userId
            )
        }
    }

    fun getBookListFromUser(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            libraryRepo.getBooksListDataFromFirebase(
                success = {
                    it?.let { _bookListResponse.postValue(it) }
                },
                fail = { TODO() }
            )
        }
    }

    fun getBookList(): LiveData<List<BookDetails>> = bookListResponse

    fun getUserInfoInDatabase(): LiveData<User> = userInfo
}