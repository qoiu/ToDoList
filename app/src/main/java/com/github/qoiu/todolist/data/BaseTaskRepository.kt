package com.github.qoiu.todolist.data

import com.github.qoiu.todolist.domain.Repository
import com.github.qoiu.todolist.domain.entities.TaskList
import java.lang.Exception

class BaseTaskRepository(private val service: ListService): Repository<TaskList> {
    override suspend fun fetchData(): TaskList = try{
        service.fetchList(1).map()
    }catch (e: Exception){
        TaskList.Error(e.localizedMessage?:"Error")
    }
}