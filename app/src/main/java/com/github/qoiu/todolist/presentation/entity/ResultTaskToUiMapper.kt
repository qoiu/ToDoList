package com.github.qoiu.todolist.presentation.entity

import com.github.qoiu.todolist.domain.entities.ResultMapper
import com.github.qoiu.todolist.domain.entities.TaskData

class ResultTaskToUiMapper : ResultMapper<TaskData> {

    override fun <T> map(list: List<TaskData>): T =
        ListUi.Success(list.map { it.map(TaskResultToUiMapper()) }) as T

    override fun <T> map(e: String): T = ListUi.Fail(e) as T
}