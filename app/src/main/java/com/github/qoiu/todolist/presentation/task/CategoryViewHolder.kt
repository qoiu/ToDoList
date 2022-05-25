package com.github.qoiu.todolist.presentation.task

import android.view.View
import com.github.qoiu.todolist.databinding.CategoryItemBinding
import com.github.qoiu.todolist.presentation.entity.UiElement

class CategoryViewHolder(
    private val view: CategoryItemBinding,
    private val listViewModel: ToDoListViewModel,
    private val taskViewModel: ToDoTaskViewModel,
    private val title: (t: String) -> Unit
) : BaseViewHolder<UiElement>(view) {
    override fun bind(data: UiElement) {
        val category = data as UiElement.Category
        val name = view.name
        name.setText(category.name)
        name.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && name.text.toString() != category.name) {
                listViewModel.update(category.update(name.text.toString()))
            }
        }
        view.delete.setOnClickListener {
            listViewModel.delete(category)
        }
        view.progress.progress = category.completion.toInt()
        view.root.setOnClickListener {
            taskViewModel.listId = category.id
            taskViewModel.getAllTasks(category.id)
            title.invoke(category.name)
            it.clearFocus()
        }
    }
}