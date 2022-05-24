package com.github.qoiu.todolist.domain.entities

class TokenAuthenticator(var token: String="") {
    fun hasToken() = token.isNotBlank()
}