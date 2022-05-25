package com.github.qoiu.todolist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.github.qoiu.todolist.presentation.main.MainActivity

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    private var _binding: Binding? = null
    protected val binding get() = _binding!!
    protected abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): Binding
    open fun update() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initBinding(inflater, container)
        return binding.root
    }

    protected fun changeTitle(@StringRes title: Int) {
        changeTitle(getString(title))
    }

    protected fun changeTitle(title: String) {
        (requireActivity() as MainActivity).supportActionBar?.title = title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}