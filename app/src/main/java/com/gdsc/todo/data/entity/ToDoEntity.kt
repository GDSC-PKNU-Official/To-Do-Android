package com.gdsc.todo.data.entity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity
@RequiresApi(Build.VERSION_CODES.O)
data class ToDoEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val contents: String,
    val date: Long
) {
    fun to() = ToDo(
        id = id,
        title = title,
        contents = contents,
        date = LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.UTC)
    )
}
