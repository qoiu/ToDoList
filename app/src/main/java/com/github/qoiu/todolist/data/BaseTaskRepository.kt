package com.github.qoiu.todolist.data

import com.github.qoiu.todolist.domain.Repository
import com.github.qoiu.todolist.domain.entities.ListData
import com.github.qoiu.todolist.domain.entities.ListResult
import com.github.qoiu.todolist.domain.entities.TaskResult
import com.google.gson.Gson

class BaseTaskRepository(private val service: ListService): Repository<ListResult> {
    override suspend fun fetchData(): ListResult = try{
        val result = service.fetchList()
        val out = if(result.code()==200) {
            Gson().fromJson(result.body()?.string(), Array<ListData>::class.java).toList()
        }else{
            throw IllegalStateException(result.errorBody()?.string())
        }
        print(out.toString())
        out.forEach {
            it.list = fetchTasks(it.id)
        }
        ListResult.Success(out)
    }catch (e: Exception){
        ListResult.Fail(e.message?:"Error")
    }

    private suspend fun fetchTasks(listId: Int): List<TaskResult> = try{
        service.tasks(listId)
    }catch (e: java.lang.Exception){
        e.printStackTrace()
        emptyList()
    }
}