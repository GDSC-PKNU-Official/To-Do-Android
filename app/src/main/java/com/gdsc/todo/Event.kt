package com.gdsc.todo

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
    private set

    fun getContentIfNotHandled(): T? = when(hasBeenHandled) {
        true -> {
            null
        }
        false -> {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent() = content
}