package com.github.qoiu.todolist.presentation.main

import com.github.qoiu.todolist.Communication
import com.github.qoiu.todolist.presentation.BaseViewModel

class MainActivityViewModel(communication: Communication<Navigator.Type>) :
    BaseViewModel<Navigator.Type>(communication) {

    fun changeScene(type: Navigator.Type) {
        provide(type)
    }
}