package com.gdsc.todo.model

import com.gdsc.todo.model.MyToDoList

class ListDatasource() {
    fun loadMyToDoList(): MutableList<MyToDoList> {
        return mutableListOf<MyToDoList>(
        )
    }
}