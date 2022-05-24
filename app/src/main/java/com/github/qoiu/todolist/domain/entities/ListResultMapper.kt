 package com.github.qoiu.todolist.domain.entities

import com.github.qoiu.todolist.Mapper

interface ListResultMapper: Mapper {
    fun <T> map(list: List<ListData>): T
    fun <T> map(e: String): T
}