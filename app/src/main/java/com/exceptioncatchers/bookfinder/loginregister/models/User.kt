package com.exceptioncatchers.bookfinder.loginregister.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class User(
    val uid: String,
    val username: String,
    val city: String,
    val profileImage: String
):Parcelable {
    constructor() : this("", "", "", "")
}