package com.github.qoiu.todolist.presentation.entity

sealed class UiElement {

    data class Category(
        val id: Int,
        val name: String,
        private val completed: Boolean,
        val completion: Double,
        private var open: Boolean = true
    ) : UiElement()

    data class Task(
        private val id: Int,
        private val list: String,
        private val listId: Int,
        val name: String,
        var completed: Boolean
    ) : UiElement()
}
