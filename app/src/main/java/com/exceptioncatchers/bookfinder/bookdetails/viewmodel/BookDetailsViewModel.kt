package com.exceptioncatchers.bookfinder.bookdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDetailsViewModel : ViewModel() {

    private val _bookDetailsResponse: MutableLiveData<BookDetails> = MutableLiveData()
    private val bookDetailsResponse: LiveData<BookDetails> get() = _bookDetailsResponse

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _bookDetailsResponse.postValue(TODO())
        }
    }

    fun getBookDetails(): LiveData<BookDetails> = bookDetailsResponse
}