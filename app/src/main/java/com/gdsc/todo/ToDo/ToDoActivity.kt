package com.gdsc.todo.ToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gdsc.todo.AddToDo.AddToDoActivity
import com.gdsc.todo.databinding.ActivityMainBinding

class ToDoActivity : AppCompatActivity() {
    // 사용자의 Input이 주어지면 뷰를 통해 Presenter로 흐름이 이어지므로
    // 뷰가 Presenter를 알고 있어야 한다.
    private lateinit var todoPresenter: ToDoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainAddButton.setOnClickListener {
            val intent = Intent(this, AddToDoActivity::class.java)
            startActivity(intent)
        }
    }
}