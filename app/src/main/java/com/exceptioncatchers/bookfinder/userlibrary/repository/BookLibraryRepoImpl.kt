package com.exceptioncatchers.bookfinder.userlibrary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.books_list.data.BooksListRepository
import com.exceptioncatchers.bookfinder.loginregister.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

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

    private fun loadUserInfo(userId: String) {
        scope.launch { repository.getUserById(
            success = { _user.postValue(it) },
            fail = { TODO() },
            userId = userId
        ) }
    }

    private fun loadBookList() {
        scope.launch { repository.getBooksListDataFromFirebase(
            success = { _bookList.postValue(it) },
            fail = { TODO() }
        ) }
    }

    private fun loadBookDetails(bookId: String) {
        scope.launch { repository.getBookDetails(
            success = { _bookDetails.postValue(it) },
            fail = { TODO() },
            bookId = bookId
        ) }
    }
}