package com.github.qoiu.todolist.presentation.task

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.qoiu.todolist.R
import com.github.qoiu.todolist.databinding.FragmentToDoListBinding
import com.github.qoiu.todolist.presentation.BaseFragment
import com.github.qoiu.todolist.presentation.main.MainActivity
import com.github.qoiu.todolist.presentation.entity.ListUi
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToDoListFragment : BaseFragment<FragmentToDoListBinding>() {

    private val viewModel: TaskViewModel by viewModel()
    private lateinit var adapter: TaskAdapter
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentToDoListBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TaskAdapter(viewModel.listViewModel, viewModel.taskViewModel) { title ->
            changeTitle(title)
        }
        binding.recyclerList.adapter = adapter
        binding.recyclerList.layoutManager = LinearLayoutManager(requireContext())
        viewModel.observe(this) { element ->
            if (element is ListUi.Success) {
                if (viewModel.taskViewModel.isCategory())
                    changeTitle(R.string.categories)
                adapter.update(element)
            }
            if (element is ListUi.Fail)
                Toast.makeText(requireContext(), element.e, Toast.LENGTH_SHORT).show()
        }
        if (savedInstanceState == null)
            viewModel.listViewModel.getAllUserList()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (viewModel.taskViewModel.isCategory()) {
            (requireActivity() as MainActivity).navToAuth()
        } else {
            viewModel.listViewModel.getAllUserList()
        }
        return true
    }
}