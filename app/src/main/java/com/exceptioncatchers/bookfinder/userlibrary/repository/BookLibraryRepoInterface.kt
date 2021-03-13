package com.exceptioncatchers.bookfinder.userlibrary.repository

import androidx.lifecycle.LiveData
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.loginregister.models.User

interface BookLibraryRepoInterface {
    suspend fun getBookDetails(bookId: String): LiveData<BookDetails>

    suspend fun getUser(userName: String): LiveData<User>

    suspend fun getUserBookList(userName: String): LiveData<List<BookDetails>>

    suspend fun addNewBook(book: BookDetails)
}