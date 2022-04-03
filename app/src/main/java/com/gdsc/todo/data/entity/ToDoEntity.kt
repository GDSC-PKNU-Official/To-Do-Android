package com.gdsc.todo.data.entity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
@RequiresApi(Build.VERSION_CODES.O)
data class ToDo (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val contents: String,
    val date: LocalDateTime = LocalDateTime.now()
)
