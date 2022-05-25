package com.github.qoiu.todolist.presentation.task

import android.graphics.Color
import android.view.View
import com.github.qoiu.todolist.databinding.TaskItemBinding
import com.github.qoiu.todolist.presentation.entity.UiElement


class TaskViewHolder(
    private val view: TaskItemBinding,
    private val taskViewModel: ToDoTaskViewModel
) : BaseViewHolder<UiElement>(view) {

    override fun bind(data: UiElement) {
        val task = data as UiElement.Task
        val name = view.name
        name.setText(task.name)
        val complete = view.complete
        complete.setColorFilter(
            if (task.completed)
                Color.GREEN
            else
                Color.GRAY
        )
        complete.setOnClickListener {
            if (!task.completed)
                taskViewModel.complete(task.comlete())
        }
        view.delete.setOnClickListener {
            taskViewModel.delete(task)
        }
        name.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && name.text.toString() != task.name) {
                taskViewModel.update(task.update(name = name.text.toString()))
            }
        }
    }
}