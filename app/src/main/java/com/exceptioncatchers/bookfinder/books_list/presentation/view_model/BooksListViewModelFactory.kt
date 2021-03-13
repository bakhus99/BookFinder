package com.exceptioncatchers.bookfinder.books_list.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BooksListViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass){
        BooksListViewModel::class.java -> BooksListViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}