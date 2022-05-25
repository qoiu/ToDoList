package com.github.qoiu.todolist.domain

import com.github.qoiu.todolist.domain.entities.CategoryData
import com.github.qoiu.todolist.domain.entities.ResultData
import com.github.qoiu.todolist.domain.entities.TaskData

interface TaskInteractor {
    suspend fun getAllLists(): ResultData<CategoryData>
    suspend fun getAllTask(listId: Int): ResultData<TaskData>
    suspend fun update(data: CategoryData)
    suspend fun delete(data: CategoryData)
    suspend fun update(data: TaskData)
    suspend fun delete(data: TaskData)
    suspend fun complete(data: TaskData)

    suspend fun create(data: CategoryData)
    suspend fun create(data: TaskData)

    class Base(
        private val listRepository: CategoryRepository,
        private val taskRepository: TaskRepository
    ) : TaskInteractor {
        override suspend fun getAllLists(): ResultData<CategoryData> = listRepository.fetch()
        override suspend fun getAllTask(listId: Int): ResultData<TaskData> =
            taskRepository.fetch(listId)

        override suspend fun update(data: CategoryData) {
            listRepository.updateData(data)
        }

        override suspend fun update(data: TaskData) {
            taskRepository.updateData(data)
        }

        override suspend fun delete(data: CategoryData) {
            listRepository.deleteData(data)
        }

        override suspend fun delete(data: TaskData) {
            taskRepository.deleteData(data)
        }

        override suspend fun complete(data: TaskData) {
            taskRepository.complete(data)
        }

        override suspend fun create(data: CategoryData) {
            listRepository.createData(data)
        }

        override suspend fun create(data: TaskData) {
            taskRepository.createData(data)
        }
    }
}