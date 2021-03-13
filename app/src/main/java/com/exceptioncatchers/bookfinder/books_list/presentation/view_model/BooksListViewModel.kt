package com.exceptioncatchers.bookfinder.books_list.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exceptioncatchers.bookfinder.books_list.data.BooksListRepository

class BooksListViewModel: ViewModel() {

    val repository = BooksListRepository()
    val testData = MutableLiveData<String>()

    fun get
}