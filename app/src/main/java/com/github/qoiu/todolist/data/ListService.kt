package com.github.qoiu.todolist.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ListService {

    @GET("random/{amount}")
    suspend fun fetchList(@Path("amount")amount: Int): TaskResult.Success

}