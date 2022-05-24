package com.github.qoiu.todolist.presentation.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.qoiu.todolist.databinding.CategoryItemBinding
import com.github.qoiu.todolist.databinding.TaskItemBinding
import com.github.qoiu.todolist.presentation.entity.ListUi
import com.github.qoiu.todolist.presentation.entity.UiElement

class TaskAdapter(private var list: List<UiElement> = emptyList()): RecyclerView.Adapter<BaseViewHolder<UiElement>>(){


    fun update(data: ListUi.Success){
        list = data.list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = when(list[position]){
            is UiElement.Category->1
            is UiElement.Task->2
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<UiElement> = when(viewType){
        1->CategoryViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        else->TaskViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: BaseViewHolder<UiElement>, position: Int) {
        holder.bind(list[position])
    }
}