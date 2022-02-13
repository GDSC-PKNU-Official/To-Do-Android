package com.gdsc.todo.data.local

import androidx.lifecycle.LiveData
import com.gdsc.todo.data.entity.ToDo

interface ToDoLocalDataSource {

    fun getToDoList(): LiveData<List<ToDo>?>

    fun addToDo(toDo: ToDo)
}