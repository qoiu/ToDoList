package com.github.qoiu.todolist.presentation.entity

sealed class ListUi{
    class Success(val list: List<UiElement>)
    class Fail(val e: String)
}