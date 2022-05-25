package com.github.qoiu.todolist.presentation.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.qoiu.todolist.databinding.CategoryItemBinding
import com.github.qoiu.todolist.databinding.NewItemBinding
import com.github.qoiu.todolist.databinding.TaskItemBinding
import com.github.qoiu.todolist.presentation.entity.ListUi
import com.github.qoiu.todolist.presentation.entity.UiElement

class TaskAdapter(
    private val listViewModel: ToDoListViewModel,
    private val taskViewModel: ToDoTaskViewModel,
    private val title: (t: String) -> Unit
) :
    RecyclerView.Adapter<BaseViewHolder<UiElement>>() {
    private var list = emptyList<UiElement>()

    fun update(data: ListUi.Success) {
        val result = data.list.toMutableList()
        result.add(if (taskViewModel.isCategory()) UiElement.NewCategory else UiElement.NewTask)
        list = result.toList()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = when (list[position]) {
        is UiElement.Category -> 1
        is UiElement.Task -> 2
        is UiElement.NewCategory -> 3
        is UiElement.NewTask -> 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<UiElement> =
        when (viewType) {
            1 -> CategoryViewHolder(
                CategoryItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), listViewModel, taskViewModel, title
            )
            2 -> TaskViewHolder(
                TaskItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), taskViewModel
            )
            3 -> NewItemViewHolder(
                NewItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), listViewModel
            )
            else -> NewItemViewHolder(
                NewItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), taskViewModel
            )
        }


    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: BaseViewHolder<UiElement>, position: Int) {
        holder.bind(list[position])
    }
}