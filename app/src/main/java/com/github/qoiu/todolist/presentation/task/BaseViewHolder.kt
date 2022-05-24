package com.github.qoiu.todolist.presentation.task

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.github.qoiu.todolist.ViewHolder

abstract class BaseViewHolder<Data>(view: ViewBinding) : RecyclerView.ViewHolder(view.root),
    ViewHolder<Data>