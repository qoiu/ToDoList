package com.github.qoiu.todolist.presentation.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.qoiu.todolist.databinding.FragmentToDoListBinding
import com.github.qoiu.todolist.domain.entities.TaskList
import com.github.qoiu.todolist.presentation.BaseFragment

class ToDoListFragment : BaseFragment<FragmentToDoListBinding>() {
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentToDoListBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerList.adapter = TaskAdapter(TaskList.Success(listOf()))
    }
}