package com.github.qoiu.todolist.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.qoiu.todolist.Communication

abstract class BaseViewModel<C : Communication<T>, T : Any>
    (private val communication: Communication<T>) : ViewModel(), Communication<T> {

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        communication.observe(owner, observer)
    }

    override fun provide(data: T) {
        communication.provide(data)
    }
}