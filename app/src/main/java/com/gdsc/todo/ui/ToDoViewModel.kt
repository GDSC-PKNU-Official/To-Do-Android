package com.gdsc.todo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gdsc.todo.di.provider.DispatcherProvider
import com.gdsc.todo.Event
import com.gdsc.todo.data.entity.ToDo
import com.gdsc.todo.data.local.ToDoLocalDataSource
import com.gdsc.todo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val localDataSource: ToDoLocalDataSource
): BaseViewModel(dispatcherProvider) {

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

    private fun addToDoList() = onIo {
        localDataSource.addToDo(ToDo(title = title, contents = contents))
    }

    fun getToDoList() = localDataSource.getToDoList()

    fun clickAddButton() {
        _addButtonClickEvent.value = Event(Unit)
    }
}