package com.github.qoiu.todolist.data

import com.github.qoiu.todolist.domain.CategoryRepository
import com.github.qoiu.todolist.domain.entities.CategoryData
import com.github.qoiu.todolist.domain.entities.ResultData

import com.google.gson.Gson

class BaseListRepository(private val service: ListService) : CategoryRepository {

    override suspend fun createData(data: CategoryData) {
        service.create(ListService.Name(data.name))
    }

    override suspend fun updateData(data: CategoryData) {
        try {
            service.update(data.id, ListService.Name(data.name))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun deleteData(data: CategoryData) {
        try {
            service.delete(data.id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun fetch(): ResultData<CategoryData> = try {
        val result = service.fetchList()
        val out = if (result.code() == 200) {
            Gson().fromJson(result.body()?.string(), Array<CategoryData>::class.java).toList()
        } else {
            throw IllegalStateException(result.errorBody()?.string())
        }
        ResultData.Success(out)
    } catch (e: Exception) {
        ResultData.Fail(e.message ?: "Error")
    }
}