package com.gdsc.todo.ToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gdsc.todo.AddToDo.AddToDoActivity
import com.gdsc.todo.databinding.ActivityToDoBinding
import com.gdsc.todo.model.ListDatasource

class ToDoActivity : AppCompatActivity() {
    // 사용자의 Input이 주어지면 뷰를 통해 Presenter로 흐름이 이어지므로
    // 뷰가 Presenter를 알고 있어야 한다.
    private lateinit var todoPresenter: ToDoPresenter
    val myListSet = ListDatasource().loadMyToDoList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainAddButton.setOnClickListener {
            val intent = Intent(this, AddToDoActivity::class.java)
            startActivity(intent)
        }

        val title = intent.getStringExtra("타이틀")
        val content = intent.getStringExtra("컨텐트")

        binding.todoTitle.setText(title)
        binding.todoDetail.setText(content)

    }
}