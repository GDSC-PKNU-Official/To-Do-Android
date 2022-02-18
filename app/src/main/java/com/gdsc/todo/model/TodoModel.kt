package com.gdsc.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDoList")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "timeStamp")
    val timeStamp: String,

    @ColumnInfo(name = "checked")
    var checked: Boolean
) {
    constructor() : this(0, "", "", false)
}