package com.exceptioncatchers.bookfinder.books_list.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.books_list.data.BooksListRepository

class BooksListViewModel: ViewModel() {

    private val repository = BooksListRepository()
    private val booksList = MutableLiveData<List<BookDetails>>()

    private val testData = MutableLiveData<BookDetails>()

    fun getBooksList(): LiveData<List<BookDetails>> = booksList

    fun loadBooksList(){
        repository.getBooksListDataFromFirebase(
            success = {
                booksList.value = it
            },
            fail = {

            }
        )
    }


    fun getTestData(): LiveData<BookDetails> = testData
    fun loadBookDetails(bookId: String){
        repository.getBookDetails(
            success = {
                testData.value = it
            },
            fail = {

            },
            bookId
        )
    }
}