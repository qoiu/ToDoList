package com.github.qoiu.todolist.presentation.task

import com.github.qoiu.todolist.databinding.CategoryItemBinding
import com.github.qoiu.todolist.presentation.entity.UiElement

class CategoryViewHolder(private val view: CategoryItemBinding): BaseViewHolder<UiElement>(view) {
    override fun bind(data: UiElement) {
        val category = data as UiElement.Category
        view.name.text = category.name
        view.progress.progress = category.completion.toInt()
    }
}