package com.gdsc.todo.ui

import com.gdsc.todo.model.ToDo
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class ToDoViewModelTest {

    private lateinit var viewModel: ToDoViewModel

    @Before
    fun setUp() {
        viewModel = ToDoViewModel()
    }

    @Test
    fun addToDoListTest_whenClickFAB_shouldAddToDoList() {
        //Given title, contents
        val title = "title"
        val contents = "contents"
        val todo = ToDo(title, contents)

        // When edit title, contents
        viewModel.title = title
        viewModel.contents = contents

        // And click FAB
        viewModel.addToDoList()

        // Then added To Do List
        assertEquals(todo, viewModel.toDoList[0])
    }
}