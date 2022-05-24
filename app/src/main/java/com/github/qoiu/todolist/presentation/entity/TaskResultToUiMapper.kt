package com.github.qoiu.todolist.presentation.entity

import com.github.qoiu.todolist.domain.entities.TaskResultMapper

class TaskResultToUiMapper : TaskResultMapper {


    override fun <T> map(id: Int, list: String, listId: Int, name: String, completed: Boolean): T =
        UiElement.Task(id, list, listId, name, completed) as T
}