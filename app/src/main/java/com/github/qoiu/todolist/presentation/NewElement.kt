package com.github.qoiu.todolist.presentation

import com.github.qoiu.todolist.presentation.entity.UiElement

interface NewElement {
    fun create(data: UiElement)
}