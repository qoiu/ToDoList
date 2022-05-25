package com.github.qoiu.todolist.presentation.entity

import com.github.qoiu.todolist.domain.entities.CategoryDataMapper

class CategoryDataToUiMapper : CategoryDataMapper {

    override fun <T> map(
        id: Int,
        name: String,
        completed: Boolean,
        completion: Double
    ): T = UiElement.Category(id, name, completed, completion) as T
}