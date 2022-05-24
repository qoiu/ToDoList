package com.github.qoiu.todolist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.qoiu.todolist.R
import com.github.qoiu.todolist.presentation.auth.AuthFragment
import com.github.qoiu.todolist.presentation.task.ToDoListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navToAuth()
    }

    fun navToAuth(){
        setFragment(AuthFragment())
    }

    fun navToTask(){
        setFragment(ToDoListFragment())
    }


    private fun setFragment(fragment: BaseFragment<*>) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}