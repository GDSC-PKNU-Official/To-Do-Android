package com.gdsc.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_todo")
data class TodoModel(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "timeStamp")
    val timeStamp: String,

    @ColumnInfo(name = "isChecked")
    var checked: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}