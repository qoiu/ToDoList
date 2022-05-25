package com.github.qoiu.todolist.data

import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface TaskService {

    @POST("tasks/")
    suspend fun create(@Body name: Name)

    @GET("tasks/")
    suspend fun fetch(@Query("todo_list") list: Int): Response<ResponseBody>

    @PUT("tasks/{id}/")
    suspend fun update(@Path("id") id: Int, @Body name: Name)

    @DELETE("tasks/{id}/")
    suspend fun delete(@Path("id") id: Int)

    @POST("tasks/complete/{id}/")
    suspend fun complete(@Path("id") id: Int)

    class Name(@SerializedName("todo_list") val id: Int, val name: String)
}