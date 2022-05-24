package com.github.qoiu.todolist.presentation.entity

import com.github.qoiu.todolist.domain.entities.ListDataMapper
import com.github.qoiu.todolist.domain.entities.TaskResult

class ListDataToUiMapper: ListDataMapper{

    override fun <T> map(
        id: Int,
        name: String,
        completed: Boolean,
        completion: Double,
        list: List<TaskResult>
    ): T {
        val newList = mutableListOf<UiElement>()
        newList.add(UiElement.Category(id,name,completed,completion))
        newList.addAll(list.map { it.map(TaskResultToUiMapper()) })
        return newList as T
    }
}