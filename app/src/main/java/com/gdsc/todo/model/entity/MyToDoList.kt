package com.gdsc.todo.model.entity

import androidx.databinding.ObservableField
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// db 테이블에 해당되는 클래스
@Entity
data class MyToDoList(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name="title") val title: String,
    @ColumnInfo(name="content") val content: String,
    @ColumnInfo(name="date") val date: String
)
