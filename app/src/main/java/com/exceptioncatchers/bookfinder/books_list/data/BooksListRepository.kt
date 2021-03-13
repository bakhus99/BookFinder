package com.exceptioncatchers.bookfinder.books_list.data

import com.exceptioncatchers.bookfinder.books_list.presentation.model.BookItem
import com.exceptioncatchers.bookfinder.loginregister.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BooksListRepository {

    fun getBooksListDataFromFirebase(
        success: (User) -> Unit,
        fail: (String) -> Unit
    ) {
        var user = User()
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User::class.java)!!
                success(user)
            }

            override fun onCancelled(error: DatabaseError) {
                fail(error.toString())
            }
        })
    }
}