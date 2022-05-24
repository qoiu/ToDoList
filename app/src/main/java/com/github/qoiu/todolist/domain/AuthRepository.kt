package com.github.qoiu.todolist.domain

import com.github.qoiu.todolist.domain.entities.AuthResult

interface AuthRepository {

    suspend fun tryLogin(login: String, password: String): AuthResult
    suspend fun registration(login: String, password: String): AuthResult
}