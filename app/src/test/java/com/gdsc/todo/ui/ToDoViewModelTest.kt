package com.gdsc.todo.ui

import com.gdsc.todo.data.entity.ToDo
import org.junit.Assert.*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ToDoViewModelTest {

    private lateinit var viewModel: ToDoViewModel

    @Rule
    @JvmField
    // LiveData가 Main Thread가 아니여도 set value를 할 수 있게 수정하는 테스트 룰
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = ToDoViewModel()
    }

    @Test
    fun addToDoListTest_whenClickFAB_shouldAddToDoList() {
        //Given title, contents
        val title = "title"
        val contents = "contents"
        val todo = ToDo(title = title, contents = contents)

        // When edit title, contents
        viewModel.title = title
        viewModel.contents = contents

        // And click FAB
        viewModel.clickCompleteButton()

        // Then added To Do List
        assertEquals(todo, viewModel.toDoList.value?.first())
    }
}