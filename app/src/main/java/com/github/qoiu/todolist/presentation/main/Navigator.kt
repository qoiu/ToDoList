package com.github.qoiu.todolist.presentation.main

import com.github.qoiu.todolist.presentation.BaseFragment
import com.github.qoiu.todolist.presentation.auth.AuthFragment
import com.github.qoiu.todolist.presentation.task.ToDoListFragment

interface Navigator {
    fun navigate(type: Type): BaseFragment<*>

    class Base : Navigator {
        private lateinit var fragment: BaseFragment<*>
        override fun navigate(type: Type): BaseFragment<*> {
            fragment = when (type) {
                Type.AUTH -> AuthFragment()
                Type.CATEGORIES -> ToDoListFragment()
            }
            return fragment
        }
    }

    enum class Type { AUTH, CATEGORIES }
}