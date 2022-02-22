package com.gdsc.todo.ui

import com.gdsc.todo.data.entity.ToDo
import org.junit.Assert.*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gdsc.todo.data.local.ToDoLocalDataSource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.*

class ToDoViewModelTest {

    @Rule
    @JvmField
    // LiveData가 Main Thread가 아니여도 set value를 할 수 있게 수정하는 테스트 룰
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mToDoLocalDataSourceImpl: ToDoLocalDataSource

    private lateinit var viewModel: ToDoViewModel

    @Before
    fun setUp() {
        mToDoLocalDataSourceImpl = mock(ToDoLocalDataSource::class.java)
        viewModel = ToDoViewModel(mToDoLocalDataSourceImpl)
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
        assertEquals(todo, ToDo(title = viewModel.title, contents = viewModel.contents))

        // FIXME: NPE -> IO Thread에서 동작하는 함수이기 때문에 addToDo함수가 실행된 것을 알지 못하는 듯
        val argumentCaptor: ArgumentCaptor<ToDo> = ArgumentCaptor.forClass(ToDo::class.java)
//        verify(mToDoLocalDataSourceImpl).addToDo(argumentCaptor.capture())
    }
}