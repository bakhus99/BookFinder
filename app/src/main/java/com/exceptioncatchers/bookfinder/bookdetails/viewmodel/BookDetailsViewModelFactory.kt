package com.exceptioncatchers.bookfinder.bookdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exceptioncatchers.bookfinder.books_list.data.BooksListRepository
import com.exceptioncatchers.bookfinder.userlibrary.repository.BookLibraryRepoImpl

class BookDetailsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BookDetailsViewModel::class.java)) {
            return BookDetailsViewModel(BooksListRepository()) as T
        }
        throw IllegalArgumentException("Неизвестное имя класса")
    }
}