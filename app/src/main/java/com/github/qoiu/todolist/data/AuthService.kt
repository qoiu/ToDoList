package com.github.qoiu.todolist.data

import com.github.qoiu.todolist.domain.entities.AuthResult
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("accounts/authentication/auth/")
    suspend fun login(@Body body: AuthRequestBody): Response<ResponseBody>

    @POST("accounts/authentication/reg/")
    suspend fun register(@Body body: AuthRequestBody): AuthResult.Error
}