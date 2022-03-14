package com.gdsc.todo.ToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.gdsc.todo.AddToDo.AddToDoActivity
import com.gdsc.todo.ToDoViewModel
import com.gdsc.todo.databinding.ActivityToDoBinding
import com.gdsc.todo.model.db.ToDoDatabase
import java.util.*

const val TAG2 = "ToDoActivity"

class ToDoActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var viewModel: ToDoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.todoRecyclerView
        viewModel = ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ToDoViewModel::class.java)
        setRecyclerView()

        binding.mainAddButton.setOnClickListener {
            startAddToDoActivity()
        }
    }

    fun setRecyclerView() {
        toDoAdapter = ToDoAdapter(viewModel.myToDoSet)
        toDoAdapter.notifyDataSetChanged()
        recyclerView.apply {
            adapter = toDoAdapter
            setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun startAddToDoActivity(){
        val intent = Intent(this, AddToDoActivity::class.java)
        startActivity(intent)
    }
}