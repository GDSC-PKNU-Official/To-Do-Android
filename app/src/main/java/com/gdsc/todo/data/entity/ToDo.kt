package com.gdsc.todo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val contents: String
)
