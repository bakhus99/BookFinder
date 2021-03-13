package com.exceptioncatchers.bookfinder.userlibrary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.loginregister.data.User

class BookLibraryRepoImpl: BookLibraryRepoInterface {
    private val _bookDetails: MutableLiveData<BookDetails> = MutableLiveData()
    private val bookDetail: LiveData<BookDetails> get() = _bookDetails
    private val _user: MutableLiveData<User> = MutableLiveData()
    private val user: LiveData<User> get() = _user
    private val _bookList: MutableLiveData<List<BookDetails>> = MutableLiveData()
    private val bookList: LiveData<List<BookDetails>> get() = _bookList

    override suspend fun getBookDetails(bookId: String): LiveData<BookDetails> {
        _bookDetails.postValue(TODO())
        return bookDetail
    }

    override suspend fun getUser(userName: String): LiveData<User> {
        _user.postValue(TODO())
        return user
    }

    override suspend fun getUserBookList(userName: String): LiveData<List<BookDetails>> {
        _bookList.postValue(TODO())
        return bookList
    }
}