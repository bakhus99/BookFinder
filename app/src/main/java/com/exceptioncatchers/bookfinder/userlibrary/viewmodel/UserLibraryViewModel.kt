package com.exceptioncatchers.bookfinder.userlibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserLibraryViewModel : ViewModel() {
    private val _bookListResponse: MutableLiveData<List<BookDetails>> = MutableLiveData()
    private val bookListResponse: LiveData<List<BookDetails>> get() = _bookListResponse

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _bookListResponse.postValue(TODO())
        }
    }

    fun getBookList(): LiveData<List<BookDetails>> = bookListResponse
}