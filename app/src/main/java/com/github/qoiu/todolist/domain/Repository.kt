package com.github.qoiu.todolist.domain

interface Repository<T> {
    suspend fun fetchData():T
}