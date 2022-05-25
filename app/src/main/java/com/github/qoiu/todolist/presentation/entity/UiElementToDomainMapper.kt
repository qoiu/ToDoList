package com.github.qoiu.todolist.presentation.entity

import com.github.qoiu.todolist.domain.entities.CategoryData
import com.github.qoiu.todolist.domain.entities.TaskData

class UiElementToDomainMapper {
    class Category : UiElementMapper.Category<CategoryData> {
        override fun <T> map(id: Int, name: String, completed: Boolean, completion: Double): T =
            CategoryData(id, name, completed, completion) as T
    }

    class Task : UiElementMapper.Task<TaskData> {
        override fun <T> map(
            id: Int,
            list: String,
            listId: Int,
            name: String,
            completed: Boolean
        ): T = TaskData(id, list, listId, name, completed) as T
    }
}