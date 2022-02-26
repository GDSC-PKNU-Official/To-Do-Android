package com.gdsc.todo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gdsc.todo.R
import com.gdsc.todo.databinding.ActivityMainBinding
import com.gdsc.todo.model.TodoModel
import com.gdsc.todo.view.adapter.TodoListAdapter
import com.gdsc.todo.viewmodel.TodoViewModel
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoListAdapter: TodoListAdapter

    private val todoItems: ArrayList<TodoModel> = ArrayList()   // 자료를 동적으로 변경할 수 있어서 ArrayList를 사용
    private var backKeyPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()
        initAddButton()
        initViewModel()
        initRecyclerView()
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backKeyPressed >= 2000) {
            backKeyPressed = System.currentTimeMillis()
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show()
        } else {
            System.exit(0)
        }
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initAddButton() {
        binding.mainFAB.setOnClickListener {
            val intent = Intent(this, EditTodoActivity::class.java)
            startActivity(intent)
        }
        getTodoItems()
    }

    private fun initViewModel() {

    }

    private fun initRecyclerView() {
        todoListAdapter = TodoListAdapter(todoItems)

        binding.mainRV.apply {
            setHasFixedSize(true)
            adapter = todoListAdapter
        }
    }



    private fun getTodoItems() {
        val getTitle = intent.getStringExtra("editTitle")
        val getDate = intent.getStringExtra("editDate")

        if(getTitle != null && getDate != null){
            Log.d("Activity Intent", "$getTitle $getDate 입니다.")
            val todoModel = TodoModel(getTitle, getDate, false)
        }
    }
}


