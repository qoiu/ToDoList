package com.github.qoiu.todolist.domain

import com.github.qoiu.todolist.domain.entities.ResultData
import com.github.qoiu.todolist.domain.entities.TaskData

interface TaskRepository : Repository<TaskData> {
    suspend fun fetch(id: Int): ResultData<TaskData>
    suspend fun complete(data: TaskData)
}