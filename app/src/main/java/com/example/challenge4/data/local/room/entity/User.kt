package com.example.challenge4.data.local.room.entity

data class User(
    val email : String,
    val username : String,
    val password : String,
)

val dummyUser = listOf(
    User(
        "awikwok@gmail.com",
        "awikwok",
        "awikwokk"
    ),
    User(
        "wildan@gmail.com",
        "wildan",
        "wildan123"
    )
)
