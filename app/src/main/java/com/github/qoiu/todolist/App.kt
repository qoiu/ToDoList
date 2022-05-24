package com.github.qoiu.todolist

import android.app.Application
import com.github.qoiu.todolist.data.AuthService
import com.github.qoiu.todolist.data.BaseAuthRepository
import com.github.qoiu.todolist.data.BaseTaskRepository
import com.github.qoiu.todolist.data.ListService
import com.github.qoiu.todolist.domain.AuthInteractor
import com.github.qoiu.todolist.domain.AuthRepository
import com.github.qoiu.todolist.domain.entities.TokenAuthenticator
import com.github.qoiu.todolist.presentation.auth.AuthViewModel
import com.github.qoiu.todolist.presentation.task.ToDoListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            single { TokenAuthenticator() }
            single { BaseTaskRepository(configRetrofit(ListService::class.java, get())) }
            single<AuthRepository> {
                BaseAuthRepository(
                    configRetrofit(
                        AuthService::class.java,
                        get()
                    )
                )
            }
            single<AuthInteractor> { AuthInteractor.Base(get(), get()) }
            viewModel { AuthViewModel(get(), Communication.Base()) }
            viewModel { ToDoListViewModel() }
        }

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }


    private fun <T> configRetrofit(clazz: Class<T>, token: TokenAuthenticator): T {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (token.hasToken()) {
            httpLoggingInterceptor.redactHeader("Bearer " + token.token)
        }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://dev1.itpw.ru:8013/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(clazz)
    }
}