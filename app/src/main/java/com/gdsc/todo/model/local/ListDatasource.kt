package com.gdsc.todo.model.local

import com.gdsc.todo.model.entity.MyToDoList

class ListDatasource() {
    fun loadMyToDoList(): MutableList<MyToDoList> {
        return mutableListOf<MyToDoList>(
        )
    }
}