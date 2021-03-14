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
//    private val _bookDetails: MutableLiveData<BookDetails> = MutableLiveData()
//    private val bookDetail: LiveData<BookDetails> get() = _bookDetails
//    private val _user: MutableLiveData<User> = MutableLiveData()
//    private val user: LiveData<User> get() = _user
//    private val _bookList: MutableLiveData<List<BookDetails>> = MutableLiveData()
//    private val bookList: LiveData<List<BookDetails>> get() = _bookList
//    private val scope = CoroutineScope(
//        SupervisorJob() +
//                Dispatchers.IO
//    )


//    private var bookDetails = BookDetails()
    private var userInfo = User()
    private var bookList: MutableList<BookDetails> = mutableListOf()

    override suspend fun getBookDetails(bookId: String): BookDetails {
        var bookDetails = BookDetails()
       repository.getBookDetails(
            success = {
                Log.d(TAG, "loadBookDetails -> $it")
                it?.let { bookDetails = it }
            },
            fail = { TODO() },
            bookId = bookId
        )
        return bookDetails
    }

    override suspend fun getUser(userId: String): User {
        repository.getUserById(
            success = {
                Log.d(TAG, "loadUserInfo -> $it")
                it?.let { userInfo = it }
            },
            fail = { TODO() },
            userId = userId
        )
        return userInfo
    }

    override suspend fun getUserBookList(userName: String): List<BookDetails> {
        repository.getBooksListDataFromFirebase(
            success = {
                Log.d(TAG, "loadBookList -> ${it?.size}")
                it?.let { bookList.addAll(it) }
            },
            fail = { TODO() }
        )
        return bookList
    }

    override suspend fun addNewBook(book: BookDetails) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "ttt"
    }
}