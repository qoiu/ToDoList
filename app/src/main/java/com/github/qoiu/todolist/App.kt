package com.github.qoiu.todolist

import android.app.Application
import com.github.qoiu.todolist.data.*
import com.github.qoiu.todolist.domain.*
import com.github.qoiu.todolist.domain.entities.TokenAuthenticator
import com.github.qoiu.todolist.presentation.main.MainActivityViewModel
import com.github.qoiu.todolist.presentation.auth.AuthViewModel
import com.github.qoiu.todolist.presentation.task.TaskViewModel
import com.github.qoiu.todolist.presentation.task.ToDoListViewModel
import com.github.qoiu.todolist.presentation.task.ToDoTaskViewModel
import okhttp3.Interceptor
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
            factory<CategoryRepository> {
                BaseListRepository(
                    configRetrofit(
                        ListService::class.java,
                        get(),
                        "todo/"
                    )
                )
            }
            factory<TaskRepository> {
                BaseTaskRepository(
                    configRetrofit(
                        TaskService::class.java,
                        get(),
                        "todo/"
                    )
                )
            }
            factory<TaskInteractor> { TaskInteractor.Base(get(), get()) }
            single { TokenAuthenticator() }
            single<AuthRepository> {
                BaseAuthRepository(
                    configRetrofit(
                        AuthService::class.java,
                        get(),
                        "accounts/authentication/"
                    )
                )
            }
            single<AuthInteractor> { AuthInteractor.Base(get(), get()) }
            single { ToDoListViewModel(Communication.Base(), get()) }
            single { ToDoTaskViewModel(Communication.Base(), get()) }
            viewModel { MainActivityViewModel(Communication.Base()) }
            viewModel { AuthViewModel(get(), Communication.Base()) }
            viewModel { TaskViewModel(Communication.Base(), get(), get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }


    private fun <T> configRetrofit(
        clazz: Class<T>,
        token: TokenAuthenticator,
        extraPath: String = ""
    ): T {

        val interceptor = Interceptor {
            val newRequest = it.request().newBuilder()
            if (token.hasToken()) newRequest.addHeader("Authorization", "Bearer " + token.token)
            return@Interceptor it.proceed(newRequest.build())
        }

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
        if (token.hasToken())
            okHttpClient.addInterceptor(interceptor)


        val retrofit = Retrofit.Builder()
            .baseUrl("http://dev1.itpw.ru:8013/$extraPath")
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(clazz)
    }
}