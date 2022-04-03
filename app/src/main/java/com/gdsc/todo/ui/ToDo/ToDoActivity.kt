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
    private var titleIsSorted: Boolean = false
    private var dateIsSorted: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.todoRecyclerView
        setSupportActionBar(binding.todoToolbar)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(
            ToDoViewModel::class.java
        )
        setRecyclerView()

        binding.mainAddButton.setOnClickListener {
            startAddToDoActivity()
        }
    }

    fun setRecyclerView() {
        when (titleIsSorted || dateIsSorted) {
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

    private fun startAddToDoActivity() {
        val intent = Intent(this, AddToDoActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkEvent(toDo: MyToDoList) {
        viewModel.deleteToDo(toDo)
    }

    // toolbar에 menu.xml 추가
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    // toolbar에 추가된 항목 클릭 시 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_title -> checkTitleSorted()
            R.id.sort_date -> checkDateSorted()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkTitleSorted() {
        when (titleIsSorted) {
            true -> {
                Log.d(ISSORTED, titleIsSorted.toString())
                titleIsSorted = false
                setRecyclerView()
            }
            false -> {
                Log.d(ISSORTED, titleIsSorted.toString())
                viewModel.sortTitle()
                titleIsSorted = true
                dateIsSorted = false
                setRecyclerView()
            }
        }
    }

    private fun checkDateSorted() {
        when (dateIsSorted) {
            true -> {
                dateIsSorted = false
                setRecyclerView()
            }
            false -> {
                viewModel.sortDate()
                dateIsSorted = true
                titleIsSorted = false
                setRecyclerView()
            }
        }
    }
}