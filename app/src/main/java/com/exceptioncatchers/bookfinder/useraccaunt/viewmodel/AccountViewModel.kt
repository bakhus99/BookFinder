package com.exceptioncatchers.bookfinder.useraccaunt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.books_list.data.BooksListRepository
import com.exceptioncatchers.bookfinder.loginregister.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(private val repository: BooksListRepository) : ViewModel() {
    private val _userSharingList: MutableLiveData<List<BookDetails>> = MutableLiveData()
    private val userSharingList: LiveData<List<BookDetails>> get() = _userSharingList
    private val _userInfo: MutableLiveData<User> = MutableLiveData()
    private val userInfo: LiveData<User> get() = _userInfo

    fun requestUserSharingList(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            //метод получения списка книг юзера
            repository.getUserById(
                success = {
                    it?.let { _userInfo.postValue(it) }
                },
                fail = { TODO() },
                userId = userId
            )
        }
    }

    fun requestUserInfo(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUserById(
                success = {
                    it?.let { _userInfo.postValue(it) }
                },
                fail = { TODO() },
                userId = userId
            )
        }
    }

    fun responseUserList(): LiveData<List<BookDetails>> = userSharingList

    fun responseUserInfo(): LiveData<User> = userInfo
}