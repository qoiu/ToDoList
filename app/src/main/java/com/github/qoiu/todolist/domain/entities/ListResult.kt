package com.github.qoiu.todolist.domain.entities

import com.github.qoiu.todolist.Mapper

sealed class ListResult {

    data class Success(
        val list: List<ListData>
    ): ListResult(), Mapper.Object<ListResultMapper>{
        override fun <T> map(mapper: ListResultMapper): T =
            mapper.map(list)
    }

    data class Fail(val e: String): ListResult(), Mapper.Object<ListResultMapper>{
        override fun <T> map(mapper: ListResultMapper): T =
            mapper.map(e)
    }
}