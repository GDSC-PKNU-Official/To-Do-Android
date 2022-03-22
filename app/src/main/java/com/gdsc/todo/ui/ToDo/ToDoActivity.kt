package com.gdsc.todo.ui.ToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.gdsc.todo.R
import com.gdsc.todo.ui.ToDoViewModel
import com.gdsc.todo.databinding.ActivityToDoBinding
import com.gdsc.todo.model.db.ToDoDatabase
import com.gdsc.todo.model.entity.MyToDoList
import com.gdsc.todo.ui.AddToDo.AddToDoActivity

const val TAG2 = "ToDoActivity"
const val ISSORTED = "isSorted"

class ToDoActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var viewModel: ToDoViewModel
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private var isSorted: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.todoRecyclerView
        toolbar = binding.todoToolbar
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory(getApplication())).get(
            ToDoViewModel::class.java)
        setRecyclerView()

        binding.mainAddButton.setOnClickListener {
            startAddToDoActivity()
        }
    }

    fun setRecyclerView() {
        when(isSorted){
            true -> toDoAdapter = ToDoAdapter(viewModel.sortMyToDoSet, viewModel)
            false -> toDoAdapter = ToDoAdapter(viewModel.myToDoSet, viewModel)
        }
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

    private fun checkEvent(toDo: MyToDoList){
        viewModel.deleteToDo(toDo)
    }

    // toolbar에 menu.xml 추가
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    // toolbar에 추가된 항목 클릭 시 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sort_title -> {
                when(isSorted) {
                    true -> {
                        Log.d(ISSORTED, isSorted.toString())
                        setRecyclerView()
                        isSorted = false
                    }
                    false -> {
                        Log.d(ISSORTED, isSorted.toString())
                        viewModel.sortTitle()
                        setRecyclerView()
                        isSorted = true
                    }
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}