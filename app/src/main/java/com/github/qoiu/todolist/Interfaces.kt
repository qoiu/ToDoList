package com.github.qoiu.todolist

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer


interface Provide<T> {
    fun provide(data: T)
}

interface Observe<T> {
    fun observe(owner: LifecycleOwner, observer: Observer<T>)
}

interface Update<T>{
    fun update(data: T)
}
