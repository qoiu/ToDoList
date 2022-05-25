package com.github.qoiu.todolist.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.qoiu.todolist.R
import com.github.qoiu.todolist.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.observe(this) {
            setFragment(Navigator.Base().navigate(it))
        }
        if (savedInstanceState == null)
            navToAuth()
    }


    fun navToAuth() {
        viewModel.changeScene(Navigator.Type.AUTH)
    }

    fun navToTask() {
        viewModel.changeScene(Navigator.Type.CATEGORIES)
    }


    private fun setFragment(fragment: BaseFragment<*>) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}