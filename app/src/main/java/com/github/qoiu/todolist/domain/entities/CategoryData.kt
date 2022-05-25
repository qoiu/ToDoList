package com.github.qoiu.todolist.domain.entities

import com.github.qoiu.todolist.Mapper
import com.google.gson.annotations.SerializedName

data class CategoryData(
    val id: Int,
    val name: String,
    val completed: Boolean,
    @SerializedName("completion_progress")
    private val completion: Double
) : Mapper.Object<CategoryDataMapper> {
    override fun <T> map(mapper: CategoryDataMapper): T =
        mapper.map(id, name, completed, completion)
}