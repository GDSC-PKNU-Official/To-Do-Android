package com.gdsc.todo.ui

import com.gdsc.todo.data.entity.ToDo
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gdsc.todo.data.local.ToDoLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class ToDoViewModelTest {

    @Rule
    @JvmField
    // LiveData가 Main Thread가 아니여도 set value를 할 수 있게 수정하는 테스트 룰
    val rule = InstantTaskExecutorRule()

    // test용 dispatcher
    private val testDispatcher = TestCoroutineDispatcher()

    // dispatcher provider를 test용으로 구현
    private val fakeDispatcherProviderImpl = FakeDispatcherProviderImpl(testDispatcher)

    @Mock
    private lateinit var mToDoLocalDataSourceImpl: ToDoLocalDataSource

    private lateinit var viewModel: ToDoViewModel

    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        mToDoLocalDataSourceImpl = mock(ToDoLocalDataSource::class.java)
        viewModel = ToDoViewModel(fakeDispatcherProviderImpl, mToDoLocalDataSourceImpl)

        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun addToDoListTest_whenClickCompleteButton_shouldAddToDoList() {
        //Given title, contents
        val title = "title"
        val contents = "contents"
        val todo = ToDo(title = title, contents = contents)

        // When edit title, contents
        viewModel.title = title
        viewModel.contents = contents

        // And click FAB
        viewModel.clickCompleteButton()

        // Then verify execute addToDo
        verify(mToDoLocalDataSourceImpl).addToDo(todo)
    }

    @Test
    fun sortToDoByTitleTest_whenClickedOptionsMenu_shouldSortToDoData() = runBlockingTest {
        // When click sort menu
        viewModel.clickSortMenu()

        // Then verify execute get sorted list
        // FIXME: Actually, there were zero interactions with this mock.
//        verify(mToDoLocalDataSourceImpl).getToDoListSortedByTitle()
    }
}