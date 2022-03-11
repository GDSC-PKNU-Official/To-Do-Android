package com.gdsc.todo.ToDo

import android.util.Log
import androidx.lifecycle.ViewModel

const val TAG="ToDoViewModel"

class ToDoViewModel : ViewModel() {
    init {
        Log.d(TAG, "init")
    }
}