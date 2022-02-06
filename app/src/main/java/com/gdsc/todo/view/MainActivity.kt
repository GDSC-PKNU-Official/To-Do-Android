package com.gdsc.todo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdsc.todo.R
import com.gdsc.todo.databinding.ActivityMainBinding
import com.gdsc.todo.model.TodoModel
import com.gdsc.todo.view.adapter.TodoListAdapter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val todoItems: ArrayList<TodoModel> = ArrayList()  // 동적배열을 위해 ArrayList를 사용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()
        initRecyclerView()
        pressButton()
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initRecyclerView() {
        binding.mainRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TodoListAdapter(todoItems)
        }


        todoItems.run {
            add(TodoModel("첫 번째", Date().time, "이건 첫 번째 내용이다!"))
            add(TodoModel("두 번째", Date().time, "이건 두 번째 내용이다!"))
        }

    }

    private fun pressButton() {
        binding.mainFAB.setOnClickListener {
            val intent = Intent(this, EditTodoActivity::class.java)
            startActivity(intent)
        }
    }
}