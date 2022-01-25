package com.gdsc.todo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdsc.todo.model.ToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(

): ViewModel() {

    private var _toDoList: MutableList<ToDo> = mutableListOf()
    val toDoList: List<ToDo> = _toDoList

    // TODO: Event 객체를 만들어 Unit을 감쌀 것.
    private var _addButtonClickEvent: MutableLiveData<Unit> = MutableLiveData()
    val addButtonClickEvent: LiveData<Unit> = _addButtonClickEvent

    var title = ""
    var contents = ""

    fun clickAddButton() {
        _addButtonClickEvent.value = Unit
        addToDoList()
    }

    private fun addToDoList() {
        _toDoList.add(ToDo(title, contents))
    }
}