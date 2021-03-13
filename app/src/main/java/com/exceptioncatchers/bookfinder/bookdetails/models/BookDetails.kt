package com.exceptioncatchers.bookfinder.bookdetails.models

import com.exceptioncatchers.bookfinder.loginregister.data.User
import java.util.*

data class BookDetails(
    val bookId: String,
    val bookTitle: String,
    val bookAutor: String,
    val bookPoster: String,
    val bookDescription: String,
    val bookRating: Float,
    val bookOwner: User,
    val sharingCount: Int
)