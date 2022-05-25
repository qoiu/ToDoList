package com.github.qoiu.todolist.presentation.entity

import com.github.qoiu.todolist.domain.entities.CategoryData
import com.github.qoiu.todolist.domain.entities.ResultMapper

class ResultListToUiMapper: ResultMapper<CategoryData>{

    override fun <T> map(category: List<CategoryData>): T = ListUi.Success(category.map { it.map(CategoryDataToUiMapper()) }) as T

    override fun <T> map(e: String): T = ListUi.Fail(e) as T
}