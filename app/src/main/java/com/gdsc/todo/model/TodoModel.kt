package com.gdsc.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo_Table")
data class TodoModel(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "timeStamp")
    var timeStamp: String,

    @ColumnInfo(name = "isChecked")
    var isChecked: Boolean
): Serializable