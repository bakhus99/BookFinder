package com.exceptioncatchers.bookfinder.addbook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exceptioncatchers.bookfinder.userlibrary.repository.BookLibraryRepoImpl
import com.exceptioncatchers.bookfinder.userlibrary.viewmodel.UserLibraryViewModel

class AddBookViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddBookViewModel::class.java)) {
            return AddBookViewModel(BookLibraryRepoImpl()) as T
        }
        throw IllegalArgumentException("Неизвестное имя класса")
    }
}