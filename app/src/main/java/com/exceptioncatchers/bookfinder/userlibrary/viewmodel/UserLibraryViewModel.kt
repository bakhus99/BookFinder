package com.exceptioncatchers.bookfinder.userlibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.loginregister.models.User
import com.exceptioncatchers.bookfinder.userlibrary.repository.BookLibraryRepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserLibraryViewModel(
    private val libraryRepo: BookLibraryRepoInterface
) : ViewModel() {
    private val _bookListResponse: MutableLiveData<List<BookDetails>> = MutableLiveData()
    private val bookListResponse: LiveData<List<BookDetails>> get() = _bookListResponse
    private val _userInfo: MutableLiveData<User> = MutableLiveData()
    private val userInfo: LiveData<User> get() = _userInfo

    fun getUser(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _userInfo.postValue(libraryRepo.getUser(userName).value)
        }
    }

    fun getBookListFromUser(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _bookListResponse.postValue(libraryRepo.getUserBookList(userName).value)
        }
    }

    fun getBookList(): LiveData<List<BookDetails>> = bookListResponse

    fun getUserInfoInDatabase(): LiveData<User> = userInfo
}