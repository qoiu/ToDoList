package com.github.qoiu.todolist.data

import com.github.qoiu.todolist.domain.TaskRepository
import com.github.qoiu.todolist.domain.entities.ResultData
import com.github.qoiu.todolist.domain.entities.TaskData
import com.google.gson.Gson

class BaseTaskRepository(private val service: TaskService) : TaskRepository {

    override suspend fun createData(data: TaskData) {
        service.create(TaskService.Name(data.listId, data.name))
    }

    override suspend fun updateData(data: TaskData) {
        try {
            service.update(data.id, TaskService.Name(data.listId, data.name))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun deleteData(data: TaskData) {
        try {
            service.delete(data.id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun complete(data: TaskData) {
        try {
            service.complete(data.id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun fetch(id: Int): ResultData<TaskData> = try {
        val result = service.fetch(id)
        val out = if (result.code() == 200) {
            Gson().fromJson(result.body()!!.string(), Array<TaskData>::class.java).toList()
        } else {
            throw IllegalStateException(result.errorBody()?.string())
        }
        ResultData.Success(out)
    } catch (e: Exception) {
        ResultData.Fail(e.message ?: "Error")
    }
}