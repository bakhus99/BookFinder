package com.exceptioncatchers.bookfinder.useraccaunt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exceptioncatchers.bookfinder.books_list.data.BooksListRepository
import com.exceptioncatchers.bookfinder.userlibrary.viewmodel.UserLibraryViewModel

class AccountViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            return AccountViewModel(BooksListRepository()) as T
        }
        throw IllegalArgumentException("Неизвестное имя класса")
    }
}