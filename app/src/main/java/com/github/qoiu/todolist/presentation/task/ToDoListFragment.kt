package com.github.qoiu.todolist.presentation.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.qoiu.todolist.databinding.FragmentToDoListBinding
import com.github.qoiu.todolist.domain.entities.ListResult
import com.github.qoiu.todolist.presentation.BaseFragment
import com.github.qoiu.todolist.presentation.entity.ListResultToUiMapper
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToDoListFragment : BaseFragment<FragmentToDoListBinding>() {

    private val viewModel: ToDoListViewModel by viewModel()
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentToDoListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TaskAdapter()
        binding.recyclerList.adapter = adapter
        binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())
        viewModel.observe(this) {
            if (it is ListResult.Success)
                adapter.update(it.map(ListResultToUiMapper()))
            if (it is ListResult.Fail)
                Toast.makeText(requireContext(), it.e, Toast.LENGTH_SHORT).show()
        }
        viewModel.getAllUserList()
    }
}