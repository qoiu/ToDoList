package com.github.qoiu.todolist.data

import com.github.qoiu.todolist.domain.AuthRepository
import com.github.qoiu.todolist.domain.entities.AuthResult
import com.google.gson.Gson

class BaseAuthRepository(private val service: AuthService): AuthRepository {
    override suspend fun tryLogin(login: String, password: String): AuthResult  = try{
        val result = service.login(AuthRequestBody(password,login))
        val out = if(result.code()==200) {
            Gson().fromJson(result.body()?.string(), AuthResult.Success::class.java)
        }else{
            Gson().fromJson(result.errorBody()?.string(), AuthResult.Error::class.java)
        }
        out
    }catch (e: Exception){
        AuthResult.Error(e.message?:"Error")
    }

    override suspend fun registration(login: String, password: String): AuthResult = try{
        service.register(AuthRequestBody(password,login))
    }catch (e: java.lang.Exception){
        e.printStackTrace()
        AuthResult.Error("Something went wrong")
    }
}