package com.github.qoiu.todolist.domain.entities

import com.github.qoiu.todolist.Mapper
import com.google.gson.annotations.SerializedName

data class ListData (
    val id: Int,
    private val name: String,
    private val completed: Boolean,
    @SerializedName("completion_progress")
    private val completion: Double,
    var list: List<TaskResult>
) : Mapper.Object<ListDataMapper>{
    override fun <T> map(mapper: ListDataMapper): T =
        mapper.map(id, name, completed, completion, list)
}