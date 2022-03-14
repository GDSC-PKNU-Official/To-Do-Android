package com.gdsc.todo.config

import android.app.Application
import com.gdsc.todo.repository.TodoRepository

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()

        TodoRepository.initialize(this)
    }
}