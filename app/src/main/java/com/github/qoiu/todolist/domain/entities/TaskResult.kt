package com.github.qoiu.todolist.domain.entities

import com.github.qoiu.todolist.Mapper
import com.google.gson.annotations.SerializedName

data class TaskResult(
    private val id: Int,
    @SerializedName("_todo_list")
    private val list: String,
    @SerializedName("todo_list")
    private val listId: Int,
    private val name: String,
    private val completed: Boolean

) : Mapper.Object<TaskResultMapper> {
    override fun <T> map(mapper: TaskResultMapper): T =
        mapper.map(id, list, listId, name, completed)
}