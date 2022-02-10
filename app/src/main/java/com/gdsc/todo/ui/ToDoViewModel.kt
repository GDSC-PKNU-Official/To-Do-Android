package com.gdsc.todo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdsc.todo.Event
import com.gdsc.todo.data.entity.ToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(

): ViewModel() {

    private var _toDoList: MutableLiveData<List<ToDo>> = MutableLiveData()
    val toDoList: LiveData<List<ToDo>> = _toDoList

    private var _completeButtonClickEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val completeButtonClickEvent: LiveData<Event<Unit>> = _completeButtonClickEvent

    private var _addButtonClickEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val addButtonClickEvent: LiveData<Event<Unit>> = _addButtonClickEvent

    var title = ""
    var contents = ""

    fun clickCompleteButton() {
        _completeButtonClickEvent.value = Event(Unit)
        addToDoList()
    }

    private fun addToDoList() {
        _toDoList.value = _toDoList.value?.plus(ToDo(title = title, contents = contents)) ?: listOf(ToDo(title = title, contents = contents))
    }

    fun clickAddButton() {
        _addButtonClickEvent.value = Event(Unit)
    }
}