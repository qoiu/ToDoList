package com.github.qoiu.todolist.presentation.task

import androidx.lifecycle.viewModelScope
import com.github.qoiu.todolist.Communication
import com.github.qoiu.todolist.domain.TaskInteractor
import com.github.qoiu.todolist.domain.entities.TaskData
import com.github.qoiu.todolist.presentation.BaseViewModel
import com.github.qoiu.todolist.presentation.NewElement
import com.github.qoiu.todolist.presentation.entity.ListUi
import com.github.qoiu.todolist.presentation.entity.ResultTaskToUiMapper
import com.github.qoiu.todolist.presentation.entity.UiElement
import com.github.qoiu.todolist.presentation.entity.UiElementToDomainMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ToDoTaskViewModel(
    communication: Communication<ListUi>,
    private val interactor: TaskInteractor
) : BaseViewModel<ListUi>(communication), NewElement {

    var listId: Int = -1

    fun isCategory(): Boolean = (listId == -1)

    fun getAllTasks(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = interactor.getAllTask(id)
            withContext(Dispatchers.Main) {
                val map = result.map<ListUi>(ResultTaskToUiMapper())
                provide(map)
            }
        }
    }


    fun update(data: UiElement.Task) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.update(data.map<TaskData>(UiElementToDomainMapper.Task()))
        }
    }

    fun delete(data: UiElement.Task) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.delete(data.map<TaskData>(UiElementToDomainMapper.Task()))
        }.invokeOnCompletion {
            getAllTasks(listId)
        }
    }

    override fun create(data: UiElement) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.create(TaskData(0, "", listId, "new task", false))
        }.invokeOnCompletion { getAllTasks(listId) }
    }

    fun complete(data: UiElement.Task) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.complete(data.map(UiElementToDomainMapper.Task()))
        }.invokeOnCompletion { getAllTasks(listId) }
    }
}