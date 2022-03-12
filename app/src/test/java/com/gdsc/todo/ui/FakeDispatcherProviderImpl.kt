package com.gdsc.todo.ui

import com.gdsc.todo.di.provider.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class FakeDispatcherProviderImpl(private val testCoroutineDispatcher: TestCoroutineDispatcher) : DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = testCoroutineDispatcher

    override val io: CoroutineDispatcher
        get() = testCoroutineDispatcher

    override val default: CoroutineDispatcher
        get() = testCoroutineDispatcher
}