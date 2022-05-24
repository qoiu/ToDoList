package com.github.qoiu.todolist.domain

import com.github.qoiu.todolist.domain.entities.AuthResult
import com.github.qoiu.todolist.domain.entities.TokenAuthenticator

interface AuthInteractor {

    suspend fun login(login: String, password: String): AuthResult
    suspend fun registration(login: String, password: String): AuthResult
    class Base(
        private val repository: AuthRepository,
        private val tokenAuthenticator: TokenAuthenticator
    ) : AuthInteractor {
        override suspend fun login(login: String, password: String): AuthResult {
            val result = repository.tryLogin(login, password)
            if (result is AuthResult.Success) {
                tokenAuthenticator.token = result.token
            }
            return result
        }

        override suspend fun registration(login: String, password: String): AuthResult {
            val result = repository.registration(login, password)
            if (result is AuthResult.Success) {
                tokenAuthenticator.token = result.token
            }
            return result
        }
    }
}