package com.github.qoiu.todolist.domain.entities

import com.github.qoiu.todolist.Mapper

interface ListDataMapper: Mapper {
    fun <T> map(
        id: Int,
        name: String,
        completed: Boolean,
        completion: Double,
        list: List<TaskResult>
        ): T
}