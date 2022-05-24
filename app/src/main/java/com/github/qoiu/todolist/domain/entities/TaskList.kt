package com.github.qoiu.todolist.domain.entities

sealed class TaskList {

    data class Success(
        private val data: List<Task>
    ): TaskList() {
        fun task(position: Int): Task = data[position]

        fun count() = data.size
    }

    data class Error(
        private val e: String
    ): TaskList()
}