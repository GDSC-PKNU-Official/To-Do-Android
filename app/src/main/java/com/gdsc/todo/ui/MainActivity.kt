package com.gdsc.todo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.gdsc.todo.databinding.ActivityMainBinding
import com.gdsc.todo.dto.TodoModel
import com.gdsc.todo.ui.adapter.TodoListAdapter
import com.gdsc.todo.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var todoListAdapter: TodoListAdapter
    private lateinit var todoViewModel: TodoViewModel
    private var todoLists: List<TodoModel> = listOf()
    private var backKeyPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initViewModel()
        initRecyclerView()
        initButton()
    }

    private val requestActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val todoTitle = it.data?.getStringExtra("todoTitle")!!
            val todoTimeStamp = it.data?.getStringExtra("todoTimeStamp")!!
            val type = it.data?.getStringExtra("TYPE")
            val todoItem = TodoModel(todoTitle, todoTimeStamp, false)

            Log.d("MainActivity TodoItem", todoItem.toString())

            when (type) {
                "isADD" -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        todoViewModel.addTodo(todoItem)
                    }
                }
            }
        }
    }

    // Main 뒤로가기 두 번 누르면 종료하기
    override fun onBackPressed() {
        if (System.currentTimeMillis() - backKeyPressed >= 2000) {
            backKeyPressed = System.currentTimeMillis()
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show()
        } else {
            exitProcess(0)  // 시스템 종료!
        }
    }


    private fun initBinding() {
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }

    private fun initViewModel() {
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        todoViewModel.getAllTodo().observe(this) {
            todoListAdapter.update(it)
        }

    }

    private fun initRecyclerView() {
        todoListAdapter = TodoListAdapter { todoModel -> deleteItem(todoModel) }
        mainBinding.rvTodoList.apply {
            setHasFixedSize(true)
            adapter = todoListAdapter
        }
    }


    private fun initButton() {
        mainBinding.fabAddTodo.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra("TYPE", "ADD") // 현재 타입은 To-Do List 추가!
            }
            requestActivity.launch(intent)
        }
    }

    private fun deleteItem(todoModel: TodoModel) {
        CoroutineScope(Dispatchers.IO).launch {
            todoViewModel.deleteTodo(todoModel)
        }
    }
}