package com.creativehazio.kraitor.data.model

data class User(
    val name : String,
    val email : String?,
    val role : UserRole,
    val numberOfLikes : Int
)
