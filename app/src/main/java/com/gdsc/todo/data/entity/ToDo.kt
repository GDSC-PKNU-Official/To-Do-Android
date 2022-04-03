package com.gdsc.todo.data.entity

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneOffset

@RequiresApi(Build.VERSION_CODES.O)
data class ToDo (
    val id: Long = 0,
    val title: String,
    val contents: String,
    val date: LocalDateTime = LocalDateTime.now()
) {
    fun to() = ToDoEntity(
        id = id,
        title = title,
        contents = contents,
        date = date.toEpochSecond(ZoneOffset.UTC)
    )
}