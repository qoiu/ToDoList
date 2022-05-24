package com.github.qoiu.todolist

import android.app.Application
import com.github.qoiu.todolist.data.*
import com.github.qoiu.todolist.domain.AuthInteractor
import com.github.qoiu.todolist.domain.AuthRepository
import com.github.qoiu.todolist.domain.Repository
import com.github.qoiu.todolist.domain.TaskInteractor
import com.github.qoiu.todolist.domain.entities.ListResult
import com.github.qoiu.todolist.domain.entities.TokenAuthenticator
import com.github.qoiu.todolist.presentation.auth.AuthViewModel
import com.github.qoiu.todolist.presentation.task.ToDoListViewModel
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
            factory<Repository<ListResult>> {
                BaseTaskRepository(
                    configRetrofit(
                        ListService::class.java,
                        get(),
                        "todo/"
                    )
                )
            }
            factory { }
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
            factory<TaskInteractor> { TaskInteractor.Base(get()) }
            viewModel { AuthViewModel(get(), Communication.Base()) }
            viewModel { ToDoListViewModel(Communication.Base(), get()) }
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
            .addInterceptor( if (token.hasToken())interceptor else httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://dev1.itpw.ru:8013/$extraPath")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(clazz)
    }
}