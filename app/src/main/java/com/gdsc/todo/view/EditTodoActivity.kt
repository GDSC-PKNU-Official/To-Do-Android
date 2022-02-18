package com.gdsc.todo.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gdsc.todo.R
import com.gdsc.todo.databinding.ActivityEditTodoBinding
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat as SimpleDateFormat



class EditTodoActivity : AppCompatActivity() {
    private lateinit var editBinding: ActivityEditTodoBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_todo)

        initBinding()
        addItem()
    }

    private fun initBinding() {
        editBinding = ActivityEditTodoBinding.inflate(layoutInflater)
        setContentView(editBinding.root)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addItem() {
        val todoTimeStamp = DateFormat.getDateTimeInstance().format(System.currentTimeMillis()).toString()

        editBinding.editToDoDateCreated.text = todoTimeStamp
        editBinding.editToDoSaveBTN.setOnClickListener {
            val todoTitle = editBinding.editToDoTitleET.text.toString()
            if(todoTitle == ""){
                Toast.makeText(this, "제목이 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("editTitle", todoTitle)
            intent.putExtra("editDate", todoTimeStamp)

            startActivity(intent)
            finish()
        }
    }
}