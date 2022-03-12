package com.gdsc.todo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gdsc.todo.di.provider.DispatcherProvider
import com.gdsc.todo.Event
import com.gdsc.todo.data.entity.ToDo
import com.gdsc.todo.data.local.ToDoLocalDataSource
import com.gdsc.todo.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
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

    private var _sortMenuClickEvent: MutableSharedFlow<Event<Unit>> = MutableSharedFlow()
    val sortMenuClickEvent: SharedFlow<Event<Unit>> = _sortMenuClickEvent

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

    fun clickSortMenu() = onMain {
        _sortMenuClickEvent.emit(Event(Unit))
    }

    suspend fun getToDoListSortedByTitle() = localDataSource.getToDoListSortedByTitle()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
}