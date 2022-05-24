package com.github.qoiu.todolist.domain.entities

import com.github.qoiu.todolist.Mapper

interface TaskResultMapper : Mapper {
    fun <T> map(
        id: Int,
        list: String,
        listId: Int,
        name: String,
        completed: Boolean
    ): T
}