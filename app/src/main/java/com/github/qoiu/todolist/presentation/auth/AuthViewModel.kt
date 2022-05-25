package com.github.qoiu.todolist.presentation.auth

import androidx.lifecycle.viewModelScope
import com.github.qoiu.todolist.Communication
import com.github.qoiu.todolist.domain.AuthInteractor
import com.github.qoiu.todolist.domain.entities.AuthResult
import com.github.qoiu.todolist.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(
    private val interactor: AuthInteractor,
    communication: Communication<AuthResult>
) : BaseViewModel<AuthResult>(communication) {

    fun login(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = interactor.login(login, password)
            withContext(Dispatchers.Main) {
                provide(result)
            }
        }
    }

    fun registration(login: String, password: String) {
        if (password.isBlank()) {
            provide(AuthResult.Error("Password is blank"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = interactor.registration(login, password)
            withContext(Dispatchers.Main) {
                provide(result)
            }
        }
    }
}