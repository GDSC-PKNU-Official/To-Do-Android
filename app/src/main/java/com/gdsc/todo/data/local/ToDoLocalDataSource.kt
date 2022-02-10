package com.gdsc.todo.data.local

import com.gdsc.todo.data.entity.ToDo

interface ToDoLocalDataSource {

    fun getToDoList(): List<ToDo>

    fun addToDo(toDo: ToDo)
}