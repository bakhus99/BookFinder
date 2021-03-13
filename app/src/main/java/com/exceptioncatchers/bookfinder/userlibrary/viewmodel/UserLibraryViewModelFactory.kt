package com.exceptioncatchers.bookfinder.userlibrary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exceptioncatchers.bookfinder.bookdetails.viewmodel.BookDetailsViewModel
import com.exceptioncatchers.bookfinder.userlibrary.repository.BookLibraryRepoImpl

class UserLibraryViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserLibraryViewModel::class.java)) {
            return UserLibraryViewModel(BookLibraryRepoImpl()) as T
        }
        throw IllegalArgumentException("Неизвестное имя класса")
    }
}