package com.github.qoiu.todolist.presentation.entity

import com.github.qoiu.todolist.domain.entities.ListData
import com.github.qoiu.todolist.domain.entities.ListResultMapper

class ListResultToUiMapper: ListResultMapper{

    override fun <T> map(list: List<ListData>): T {
        val result = mutableListOf<UiElement>()
        list.forEach {
            result.addAll(it.map(ListDataToUiMapper()))
        }
        return ListUi.Success(result) as T
    }

    override fun <T> map(e: String): T = ListUi.Fail(e) as T
}