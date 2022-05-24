 package com.github.qoiu.todolist.domain

import com.github.qoiu.todolist.domain.entities.ListResult

interface TaskInteractor {
    suspend fun getAllLists(): ListResult

    class Base(private val repository: Repository<ListResult>): TaskInteractor{
        override suspend fun getAllLists(): ListResult = repository.fetchData()
    }
}