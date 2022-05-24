package com.github.qoiu.todolist.presentation.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.qoiu.todolist.databinding.TaskItemBinding
import com.github.qoiu.todolist.domain.entities.Task
import com.github.qoiu.todolist.domain.entities.TaskList

class TaskAdapter(private var list: TaskList.Success): RecyclerView.Adapter<TaskAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(TaskItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder:Holder, position: Int) {
        holder.bind(list.task(position))
    }

    override fun getItemCount(): Int = list.count()

    inner class Holder(private val view: TaskItemBinding): RecyclerView.ViewHolder(view.root){
        fun bind(data: Task){
            view.taskText.text = data.title
        }
    }
}