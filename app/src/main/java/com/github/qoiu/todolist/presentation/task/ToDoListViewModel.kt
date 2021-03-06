package com.github.qoiu.todolist.presentation.task

import androidx.lifecycle.viewModelScope
import com.github.qoiu.todolist.Communication
import com.github.qoiu.todolist.domain.TaskInteractor
import com.github.qoiu.todolist.domain.entities.CategoryData
import com.github.qoiu.todolist.presentation.BaseViewModel
import com.github.qoiu.todolist.presentation.NewElement
import com.github.qoiu.todolist.presentation.entity.ListUi
import com.github.qoiu.todolist.presentation.entity.ResultListToUiMapper
import com.github.qoiu.todolist.presentation.entity.UiElement
import com.github.qoiu.todolist.presentation.entity.UiElementToDomainMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ToDoListViewModel(
    communication: Communication<ListUi>,
    private val interactor: TaskInteractor
) : BaseViewModel<ListUi>(communication), NewElement {

    fun getAllUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = interactor.getAllLists()
            withContext(Dispatchers.Main) {
                provide(result.map(ResultListToUiMapper()))
            }
        }
    }

    fun update(data: UiElement.Category) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.update(data.map<CategoryData>(UiElementToDomainMapper.Category()))
        }
    }

    fun delete(data: UiElement.Category) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.delete(data.map<CategoryData>(UiElementToDomainMapper.Category()))
        }.invokeOnCompletion {
            getAllUserList()
        }
    }

    override fun create(data: UiElement) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.create(CategoryData(0, "New category", false, 0.0))
        }.invokeOnCompletion {
            getAllUserList()
        }
    }
}