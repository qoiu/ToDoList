package com.github.qoiu.todolist.domain.entities

import com.google.gson.annotations.SerializedName

sealed class AuthResult {

    data class Success(
        val token: String,
        private val user: User
    ) : AuthResult() {

        data class User(
            private val username: String,
            private val id: String,
            @SerializedName("is_active")
            private val active: Boolean
        )
    }

    data class Error(val detail: String) : AuthResult()

}