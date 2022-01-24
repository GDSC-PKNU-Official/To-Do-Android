package com.gdsc.todo.ui

import androidx.lifecycle.ViewModel
import com.gdsc.todo.model.ToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(

): ViewModel() {

    private var _toDoList: MutableList<ToDo> = mutableListOf()
    val toDoList: List<ToDo> = _toDoList

    var title = ""
    var contents = ""

    fun addToDoList() {
        _toDoList.add(ToDo(title, contents))
    }
}