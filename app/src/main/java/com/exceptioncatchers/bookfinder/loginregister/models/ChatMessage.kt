package com.exceptioncatchers.bookfinder.loginregister.models

data class ChatMessage(
    val id: String,
    val text: String,
    val fromId: String,
    val toId: String,
    val timeSend: Long
) {
    constructor() : this("", "", "", "", -1)
}