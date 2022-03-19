package com.gdsc.todo.ui.ToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.gdsc.todo.ui.ToDoViewModel
import com.gdsc.todo.databinding.ActivityToDoBinding
import com.gdsc.todo.model.db.ToDoDatabase
import com.gdsc.todo.ui.AddToDo.AddToDoActivity

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
        viewModel = ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory(getApplication())).get(
            ToDoViewModel::class.java)
        setRecyclerView()

        binding.mainAddButton.setOnClickListener {
            startAddToDoActivity()
        }
    }

    fun setRecyclerView() {
        toDoAdapter = ToDoAdapter(viewModel.myToDoSet, viewModel)
        toDoAdapter.notifyDataSetChanged()
        recyclerView.apply {
            adapter = toDoAdapter
            setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.getAll()
        setRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        ToDoDatabase.destroyInstance()
    }

    private fun startAddToDoActivity(){
        val intent = Intent(this, AddToDoActivity::class.java)
        startActivity(intent)
        finish()
    }
}