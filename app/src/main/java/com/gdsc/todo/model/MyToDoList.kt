package com.gdsc.todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyToDoList(
    @PrimaryKey val id: Int,

    val title: String,
    val content: String
)
