package com.gdsc.todo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gdsc.todo.databinding.ActivityEditBinding
import java.text.DateFormat


class EditActivity : AppCompatActivity() {
    private lateinit var addBinding: ActivityEditBinding
    private lateinit var todoTitle: String
    private lateinit var type: String
    private val todoTimeStamp: String = DateFormat.getDateTimeInstance().format(System.currentTimeMillis()).toString()
//    private val type = intent.getStringExtra("TYPE")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        getTodo()
    }

    private fun initBinding() {
        addBinding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(addBinding.root)

        addBinding.tvTodoTimeStamp.text = todoTimeStamp
        type = intent.getStringExtra("TYPE").toString()
    }

    private fun getTodo() {
        addBinding.btnSave.setOnClickListener {
            todoTitle = addBinding.etTodoTitle.text.toString()

            when(type){
                "ADD" -> {  // 새로 추가하는 경우
                    if(todoTitle.isNotEmpty()){
                        val intent = Intent(this,MainActivity::class.java).apply {
                            putExtra("todoTitle", todoTitle)
                            putExtra("todoTimeStamp", todoTimeStamp)
                            putExtra("TYPE", "isADD")
                        }
                        setResult(RESULT_OK, intent)
                        finish()
                    }else{
                        Toast.makeText(this, "제목이 비어있습니다.", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }
            }
        }
    }
}
