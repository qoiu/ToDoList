package com.github.qoiu.todolist.domain.entities

sealed class ResultData<Data> {
    abstract fun <T> map(mapper: ResultMapper<Data>): T

    data class Success<Data>(
        val data: List<Data>
    ) : ResultData<Data>() {
        override fun <T> map(mapper: ResultMapper<Data>): T =
            mapper.map(data)
    }

    data class Fail<Data>(val e: String) : ResultData<Data>() {
        override fun <T> map(mapper: ResultMapper<Data>): T =
            mapper.map(e)
    }
}