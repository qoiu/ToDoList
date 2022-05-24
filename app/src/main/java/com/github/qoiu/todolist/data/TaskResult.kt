package com.github.qoiu.todolist.data

import com.github.qoiu.todolist.domain.entities.TaskList
import com.google.gson.annotations.SerializedName
import java.lang.Exception

sealed class TaskResult{
    abstract fun map(): TaskList

    data class Success (
        private val id: Int,
        private val name: String,
        private val completed: Boolean,
        @SerializedName("completion_progress")
        private val completion: Double
    ): TaskResult(){
        override fun map(): TaskList = TaskList.Success(
            listOf()
        )
    }

    data class Fail(val e: Exception): TaskResult(){
        override fun map(): TaskList = TaskList.Error(e.localizedMessage?:"Error")
    }
}