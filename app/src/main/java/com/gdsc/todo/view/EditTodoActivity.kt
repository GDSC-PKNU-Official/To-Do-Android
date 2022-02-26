package com.gdsc.todo.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gdsc.todo.R
import com.gdsc.todo.databinding.ActivityEditTodoBinding
import com.gdsc.todo.databinding.ActivityMainBinding
import com.gdsc.todo.model.TodoModel
import com.gdsc.todo.viewmodel.TodoViewModel
import java.text.DateFormat



class EditTodoActivity : AppCompatActivity() {
    private lateinit var editBinding: ActivityEditTodoBinding
    private lateinit var mainBinding: ActivityMainBinding
    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_todo)

        initBinding()
        addItem()
    }

    private fun initBinding() {
        editBinding = ActivityEditTodoBinding.inflate(layoutInflater)
        setContentView(editBinding.root)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }

    private fun addItem() {
        val todoTimeStamp = DateFormat.getDateTimeInstance().format(System.currentTimeMillis()).toString()

        editBinding.editToDoDateCreated.text = todoTimeStamp
        editBinding.editToDoSaveBTN.setOnClickListener {
            val todoTitle = editBinding.editToDoTitleET.text.toString()
            if(todoTitle == ""){
                Toast.makeText(this, "제목이 비어있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val todoModel = TodoModel(todoTitle, todoTimeStamp, false)
            todoViewModel.insert(todoModel)
            Toast.makeText(this,"Successfully added!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("editTitle", todoTitle)
            intent.putExtra("editDate", todoTimeStamp)

            startActivity(intent)
            finish()
        }
    }
}