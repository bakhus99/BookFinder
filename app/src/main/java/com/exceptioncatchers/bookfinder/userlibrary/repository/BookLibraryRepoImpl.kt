package com.exceptioncatchers.bookfinder.userlibrary.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.books_list.data.BooksListRepository
import com.exceptioncatchers.bookfinder.loginregister.models.User
import kotlinx.coroutines.*

class BookLibraryRepoImpl: BookLibraryRepoInterface {
    private val repository = BooksListRepository()
    private val _bookDetails: MutableLiveData<BookDetails> = MutableLiveData()
    private val bookDetail: LiveData<BookDetails> get() = _bookDetails
    private val _user: MutableLiveData<User> = MutableLiveData()
    private val user: LiveData<User> get() = _user
    private val _bookList: MutableLiveData<List<BookDetails>> = MutableLiveData()
    private val bookList: LiveData<List<BookDetails>> get() = _bookList
    private val scope = CoroutineScope(
        SupervisorJob() +
                Dispatchers.IO
    )
    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d(LOG_TAG, "CoroutineExceptionHandler got $exception")
    }

    override suspend fun getBookDetails(bookId: String): LiveData<BookDetails> {
        loadBookDetails(bookId)
        return bookDetail
    }

    override suspend fun getUser(userId: String): LiveData<User> {
        loadUserInfo(userId)
        return user
    }

    override suspend fun getUserBookList(userName: String): LiveData<List<BookDetails>> {
        loadBookList()
        return bookList
    }

    override suspend fun addNewBook(book: BookDetails) {
        TODO("Not yet implemented")
    }

    private suspend fun loadUserInfo(userId: String) {
        scope.async(handler) { repository.getUserById(
            success = { _user.postValue(it) },
            fail = { Log.d(LOG_TAG, it) },
            userId = userId
        ) }.await()
    }

    private suspend fun loadBookList() {
        scope.async(handler) { repository.getBooksListDataFromFirebase(
            success = { _bookList.postValue(it) },
            fail = { Log.d(LOG_TAG, it) }
        ) }.await()
    }

    private suspend fun loadBookDetails(bookId: String) {
        scope.async(handler) { repository.getBookDetails(
            success = { _bookDetails.postValue(it) },
            fail = { Log.d(LOG_TAG, it) },
            bookId = bookId
        ) }.await()
    }

    companion object {
        private const val LOG_TAG = "BookLibraryRepoImpl"
    }
}