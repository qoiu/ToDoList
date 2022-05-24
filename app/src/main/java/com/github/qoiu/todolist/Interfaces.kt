package com.github.qoiu.todolist

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer


interface ViewHolder<T>{
    fun bind(data: T)
}


interface Mapper{
    interface Object<I: Mapper>: Mapper{
        fun <T>map(mapper: I): T
    }
}


interface Provide<T> {
    fun provide(data: T)
}

interface Observe<T> {
    fun observe(owner: LifecycleOwner, observer: Observer<T>)
}

interface Update<T>{
    fun update(data: T)
}
