package com.github.qoiu.todolist.presentation.entity

import com.github.qoiu.todolist.Mapper

interface UiElementMapper : Mapper {
    interface Ui {
        fun <T> map(mapper: UiElementToDomainMapper.Category): T
        fun <T> map(mapper: UiElementToDomainMapper.Task): T
    }

    interface Category<T> {
        fun <T> map(
            id: Int,
            name: String,
            completed: Boolean,
            completion: Double
        ): T
    }

    interface Task<T> {
        fun <T> map(
            id: Int,
            list: String,
            listId: Int,
            name: String,
            completed: Boolean
        ): T
    }
}