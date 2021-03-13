package com.exceptioncatchers.bookfinder.bookdetails.models

import android.os.Parcelable
import com.exceptioncatchers.bookfinder.loginregister.data.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookDetails(
    val bookId: String,
    val bookTitle: String,
    val bookAutor: String,
    val bookPoster: String,
    val bookDescription: String,
    val bookRating: Float,
    val bookOwner: User,
    val sharingCount: Int
) : Parcelable