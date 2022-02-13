package com.gdsc.todo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsc.todo.Event
import com.gdsc.todo.data.entity.ToDo
import com.gdsc.todo.data.local.ToDoLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val localDataSource: ToDoLocalDataSource
): ViewModel() {

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
        // room db는 main Thread에서 실행하면 ANR이 발생할 수 있기 때문에 (UI가 몇초 멈추면 생기는 응답없음) bg Thread에서 실행시켜야 한다.
        viewModelScope.launch(Dispatchers.IO) {
            localDataSource.addToDo(ToDo(title = title, contents = contents))
        }
    }

    fun getToDoList() = localDataSource.getToDoList()

    fun clickAddButton() {
        _addButtonClickEvent.value = Event(Unit)
    }
}