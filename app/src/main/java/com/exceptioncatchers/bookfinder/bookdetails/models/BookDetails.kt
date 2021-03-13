package com.exceptioncatchers.bookfinder.bookdetails.models

data class BookDetails(
    val bookTitle: String,
    val bookAutor: String,
    val bookPoster: String,
    val bookDescription: String,
    val bookRating: Float,
    //заменить на класс пользователя
    val bookOwner: Any,
    val sharingCount: Int
)