package com.gdsc.todo.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gdsc.todo.databinding.ActivityEditTodoBinding
import com.gdsc.todo.model.TodoModel
import java.text.DateFormat


class EditTodoActivity : AppCompatActivity() {
    private lateinit var editBinding: ActivityEditTodoBinding
    private val TYPE = intent.getStringExtra("type")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        addItem()
    }

    private fun initBinding() {
        editBinding = ActivityEditTodoBinding.inflate(layoutInflater)
        setContentView(editBinding.root)
    }

    private fun addItem() {
        editBinding.editToDoSaveBTN.setOnClickListener {
            val todoTitle = editBinding.editToDoTitleET.text.toString()
            val todoTimeStamp = DateFormat.getDateTimeInstance().format(System.currentTimeMillis()).toString()
            editBinding.editToDoDateCreated.text = todoTimeStamp

            when(TYPE){
                "ADD" -> {  // todoList를 새롭게 추가하는 경우
                    if(todoTitle.isNotEmpty()){
                        val intent = Intent(this, MainActivity::class.java).apply {
                            putExtra("editTitle", todoTitle)
                            putExtra("editDate", todoTimeStamp)
                            putExtra("TYPE", "isADD")
                        }
                        setResult(RESULT_OK, intent)
                        finish()
                    }else{  // todo의 제목이 비어있을 경우
                        Toast.makeText(this, "제목이 비어있습니다.", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }

            }
        }
    }
}