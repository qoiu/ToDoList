package com.github.qoiu.todolist.data

import com.github.qoiu.todolist.domain.entities.TaskResult
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ListService {

    @GET("lists/")
    suspend fun fetchList(): Response<ResponseBody>

    @GET("tasks/")
    suspend fun tasks(@Query("todo_list") list: Int): List<TaskResult>

}