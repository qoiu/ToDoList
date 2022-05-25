package com.github.qoiu.todolist.data

import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ListService {

    @GET("lists/")
    suspend fun fetchList(): Response<ResponseBody>

    @POST("lists/")
    suspend fun create(@Body name: Name)

    @PUT("lists/{id}/")
    suspend fun update(@Path("id") id: Int, @Body name: Name)

    @DELETE("lists/{id}/")
    suspend fun delete(@Path("id") id: Int)

    class Name(@SerializedName("name") val name: String)
}