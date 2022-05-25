package com.github.qoiu.todolist.presentation.task

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.qoiu.todolist.Communication
import com.github.qoiu.todolist.presentation.BaseViewModel
import com.github.qoiu.todolist.presentation.entity.ListUi

class TaskViewModel(
    communication: Communication<ListUi>,
    val taskViewModel: ToDoTaskViewModel,
    val listViewModel: ToDoListViewModel
) : BaseViewModel<ListUi>(communication) {
    override fun observe(owner: LifecycleOwner, observer: Observer<ListUi>) {
        taskViewModel.observe(owner) { list ->
            observer.onChanged(list)
        }
        listViewModel.observe(owner) { list ->
            taskViewModel.listId = -1
            observer.onChanged(list)
        }
    }
}