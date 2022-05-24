package com.github.qoiu.todolist.presentation.task

import androidx.lifecycle.viewModelScope
import com.github.qoiu.todolist.Communication
import com.github.qoiu.todolist.domain.TaskInteractor
import com.github.qoiu.todolist.domain.entities.ListResult
import com.github.qoiu.todolist.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ToDoListViewModel(
    communication: Communication<ListResult>,
    private val interactor: TaskInteractor
): BaseViewModel<Communication.Base<ListResult>,ListResult>(communication) {

    fun getAllUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = interactor.getAllLists()
            withContext(Dispatchers.Main) {
                provide(result)
            }
        }
    }
}