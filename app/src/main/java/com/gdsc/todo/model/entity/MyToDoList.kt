package com.gdsc.todo.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyToDoList(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    @ColumnInfo(name="title") val title: String,
    @ColumnInfo(name="content") val content: String
)
