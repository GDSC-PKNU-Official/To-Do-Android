package com.gdsc.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_todoList")
data class TodoModel(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "timeStamp")
    val timeStamp: String,

    @ColumnInfo(name = "checked")
    var checked: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}