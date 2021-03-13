package com.exceptioncatchers.bookfinder.userlibrary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.loginregister.data.User

class BookLibraryRepoImpl: BookLibraryRepoInterface {
    private val _bookDetails: MutableLiveData<BookDetails> = MutableLiveData()
    private val bookDetail: LiveData<BookDetails> get() = _bookDetails

    override suspend fun getBookDetails(bookId: String): LiveData<BookDetails> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(userName: String): LiveData<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserBookList(userName: String): LiveData<List<BookDetails>> {
        TODO("Not yet implemented")
    }
}