package com.exceptioncatchers.bookfinder.books_list.data

import android.util.Log
import com.exceptioncatchers.bookfinder.bookdetails.models.BookDetails
import com.exceptioncatchers.bookfinder.books_list.presentation.model.BookItem
import com.exceptioncatchers.bookfinder.loginregister.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BooksListRepository {

    fun getBooksListDataFromFirebase(
        success: (List<BookDetails>?) -> Unit,
        fail: (String) -> Unit
    ) {
        val booksList = mutableListOf<BookDetails>()
        val ref = FirebaseDatabase.getInstance().getReference("books")
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val book = it.getValue(BookDetails::class.java)
                    booksList.add(book!!)
                }
                success(booksList)
            }

            override fun onCancelled(error: DatabaseError) {
                fail(error.toString())
            }
        })
    }

    fun getBookDetails(
        success: (BookDetails?) -> Unit,
        fail: (String) -> Unit,
        bookId: String?
    ) {
        val ref = FirebaseDatabase.getInstance().getReference("books/$bookId")
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                val book = it.getValue(BookDetails::class.java)
                success(book)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                fail(error.toString())
            }
        })
    }
}