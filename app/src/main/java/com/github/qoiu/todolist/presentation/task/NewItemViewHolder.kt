package com.github.qoiu.todolist.presentation.task

import com.github.qoiu.todolist.databinding.NewItemBinding
import com.github.qoiu.todolist.presentation.NewElement
import com.github.qoiu.todolist.presentation.entity.UiElement

class NewItemViewHolder(
    private val view: NewItemBinding,
    private val viewModel: NewElement
) : BaseViewHolder<UiElement>(view) {
    override fun bind(data: UiElement) {
        view.newItem.setOnClickListener {
            viewModel.create(data)
        }
    }
}