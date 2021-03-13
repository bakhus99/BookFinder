package com.exceptioncatchers.bookfinder.books_list.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.books_list.data.BooksListRepository

class BooksListViewModel: ViewModel() {

    private val repository = BooksListRepository()
    private val testData = MutableLiveData<List<BookDetails>>()

    fun getTestData(): LiveData<List<BookDetails>> = testData

    fun loadBooksList(){
        repository.getBooksListDataFromFirebase(
            success = {
                testData.value = it
            },
            fail = {

            }
        )
    }
}