package com.github.qoiu.todolist.domain.entities

import com.github.qoiu.todolist.Mapper

interface ResultMapper<Data> : Mapper {
    fun <T> map(list: List<Data>): T
    fun <T> map(e: String): T
}