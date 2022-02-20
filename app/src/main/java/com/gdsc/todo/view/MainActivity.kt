package com.gdsc.todo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdsc.todo.R
import com.gdsc.todo.databinding.ActivityMainBinding
import com.gdsc.todo.model.TodoModel
import com.gdsc.todo.view.adapter.TodoListAdapter
import com.gdsc.todo.viewmodel.TodoViewModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoListAdapter: TodoListAdapter

    //    private val todoItems: List<TodoModel> = listOf()
    private val todoItems: ArrayList<TodoModel> = ArrayList()   // 자료를 동적으로 변경할 수 있어서 ArrayList를 사용
    private var backKeyPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()
        initRecyclerView()
        initAddButton()
//        initViewModel()

    }

    override fun onBackPressed() {
//        super.onBackPressed()
//        뒤로가기 버튼 막기
        if (System.currentTimeMillis() - backKeyPressed >= 2000) {
            backKeyPressed = System.currentTimeMillis()
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show()
        } else {
            finish()
        }
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initRecyclerView() {
        binding.mainRV.apply {
            setHasFixedSize(true)
            //layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun initAddButton() {
        binding.mainFAB.setOnClickListener {
            val intent = Intent(this, EditTodoActivity::class.java)
            startActivity(intent)
        }
        val getTitle = intent.getStringExtra("editTitle")
        val getDate = intent.getStringExtra("editDate")

        if (getTitle != null && getDate != null) {
//            todoItems[TodoModel(getTitle, getDate, false)]
            todoItems.add(TodoModel(getTitle, getDate, false))
            binding.mainRV.adapter = TodoListAdapter(todoItems)
        }
    }

    private fun initViewModel() {
        todoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(TodoViewModel::class.java)
//        todoViewModel.getTodoList().observe(
//            this, Observer { observable, any -> })
//        todoViewModel.getTodoList().observe(this, Observer { todoListAdapter.setTodoItems(it)}

    }

}


