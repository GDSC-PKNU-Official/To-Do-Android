package com.gdsc.todo.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gdsc.todo.R
import com.gdsc.todo.databinding.ActivityEditTodoBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat as SimpleDateFormat

class EditTodoActivity : AppCompatActivity() {
    private lateinit var editbinding: ActivityEditTodoBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_todo)

        initBinding()
        addItem()
    }

    private fun initBinding() {
        editbinding = ActivityEditTodoBinding.inflate(layoutInflater)
        setContentView(editbinding.root)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addItem() {
        editbinding.editToDoSaveBTN.setOnClickListener {
            val todoTitle = editbinding.editToDoTitleET.text.toString()
            val todoContent = editbinding.editToDoContentET.text.toString()
            val todoDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//            val todoDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").to

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("editTitle", todoTitle)
            intent.putExtra("editContent", todoContent)
            intent.putExtra("editDate", todoDate)

            startActivity(intent)
            finish()
        }
    }
}