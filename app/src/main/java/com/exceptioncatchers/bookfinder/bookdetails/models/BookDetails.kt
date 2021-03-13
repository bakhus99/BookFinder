package com.exceptioncatchers.bookfinder.bookdetails.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookDetails(
    val bookTitle: String,
    val bookAuthor: String,
    val bookPoster: String,
    val bookDescription: String,
    val bookRating: Float,
    //заменить на класс пользователя
    val sharingCount: Int,
    val userUid: String,
    val bookId: String
): Parcelable {
    constructor() : this("", "", "", "", 1F,1, "", "")
}