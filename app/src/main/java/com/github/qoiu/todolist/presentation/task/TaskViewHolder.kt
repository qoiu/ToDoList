package com.github.qoiu.todolist.presentation.task

import com.github.qoiu.todolist.databinding.TaskItemBinding
import com.github.qoiu.todolist.presentation.entity.UiElement

class TaskViewHolder(private val view: TaskItemBinding): BaseViewHolder<UiElement>(view) {

    override fun bind(data: UiElement) {
        val task = data as UiElement.Task
        view.name.text = task.name
        view.complete.text = if(task.completed)
            "✅"
        else
            "❌"
        view.complete.setOnClickListener {
            task.completed = !task.completed
        }
    }
}