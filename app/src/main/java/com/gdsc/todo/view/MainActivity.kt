package com.gdsc.todo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdsc.todo.R
import com.gdsc.todo.databinding.ActivityMainBinding
import com.gdsc.todo.model.TodoModel
import com.gdsc.todo.view.adapter.TodoListAdapter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val todoItems: ArrayList<TodoModel> = ArrayList()   // 자료를 동적으로 변경할 수 있어서 ArrayList를 사용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()
        initRecyclerView()
        initAddButton()
        Log.d("initAddButton", "$todoItems")
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("initBinding", "initBinding 호출되었습니다.")
    }

    private fun initRecyclerView() {
        binding.mainRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            Log.d("initRecyclerView", "initRecyclerView가 호출되었습니다.")
        }
    }

    private fun initAddButton() {
        binding.mainFAB.setOnClickListener {
            val intent = Intent(this, EditTodoActivity::class.java)
            startActivity(intent)
        }
        val getTitle = intent.getStringExtra("editTitle")
        val getContent = intent.getStringExtra("editContent")
        val getDate = intent.getStringExtra("editDate")

        if (getTitle != null && getContent != null && getDate != null) {
            todoItems.add(TodoModel(getTitle, getDate, getContent, false))
            binding.mainRV.adapter = TodoListAdapter(todoItems)
        }
    }
}