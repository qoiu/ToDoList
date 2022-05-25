package com.github.qoiu.todolist.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.qoiu.todolist.R
import com.github.qoiu.todolist.databinding.FragmentAuthBinding
import com.github.qoiu.todolist.domain.entities.AuthResult
import com.github.qoiu.todolist.presentation.BaseFragment
import com.github.qoiu.todolist.presentation.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : BaseFragment<FragmentAuthBinding>() {
    private val viewModel: AuthViewModel by viewModel()
    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAuthBinding =
        FragmentAuthBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeTitle(R.string.authorization)
        viewModel.observe(this) {
            if (it is AuthResult.Error) {
                Toast.makeText(requireContext(), it.detail, Toast.LENGTH_SHORT).show()
            } else {
                (requireActivity() as MainActivity).navToTask()
            }
        }
        binding.loginBtn.setOnClickListener {
            val login = binding.loginEt.text.toString()
            val password = binding.pw.text.toString()
            viewModel.login(login, password)
        }
        binding.registerBtn.setOnClickListener {
            val login = binding.loginEt.text.toString()
            val password = binding.pw.text.toString()
            viewModel.registration(login, password)
        }
    }
}