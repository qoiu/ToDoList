package com.github.qoiu.todolist.data

import com.github.qoiu.todolist.domain.entities.AuthResult
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/")
    suspend fun login(@Body body: AuthRequestBody): Response<ResponseBody>

    @POST("reg/")
    suspend fun register(@Body body: AuthRequestBody): AuthResult.Error
}