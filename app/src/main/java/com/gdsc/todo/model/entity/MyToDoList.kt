package com.gdsc.todo.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyToDoList(
    @PrimaryKey(autoGenerate = true) val id: Int?,

    var title: String,
    var content: String
)
