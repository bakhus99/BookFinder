package com.exceptioncatchers.bookfinder.loginregister.models

data class User(
    val uid: String,
    val username: String,
    val city: String,
    val profileImage: String
) {
    constructor() : this("", "", "", "")
}