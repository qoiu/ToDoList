package com.github.qoiu.todolist.domain

interface Repository<Data> {
    suspend fun createData(data: Data)
    suspend fun updateData(data: Data)
    suspend fun deleteData(data: Data)
}