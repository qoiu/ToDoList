package com.github.qoiu.todolist

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication<T> : Provide<T>, Observe<T> {

    open class Base<T : Any> : Communication<T> {
        private val liveData = MutableLiveData<T>()
        override fun provide(data: T) {
            liveData.postValue(data)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }
    }
}