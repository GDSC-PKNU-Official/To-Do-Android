package com.gdsc.todo.ToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdsc.todo.AddToDo.AddToDoActivity
import com.gdsc.todo.R
import com.gdsc.todo.databinding.ActivityToDoBinding
import com.gdsc.todo.model.ListDatasource
import com.gdsc.todo.model.MyToDoList

const val TAG2 = "ToDoActivity"

class ToDoActivity : AppCompatActivity(), ToDoContract.View {
    // 사용자의 Input이 주어지면 뷰를 통해 Presenter로 흐름이 이어지므로
    // 뷰가 Presenter를 알고 있어야 한다.
    override lateinit var presenter: ToDoContract.Presenter
    private lateinit var todoTitle: TextView
    private lateinit var todoDetail: TextView
    private val myToDoSet = ListDatasource().loadMyToDoList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = ToDoPresenter(this)

        binding.mainAddButton.setOnClickListener {
            val intent = Intent(this, AddToDoActivity::class.java)
            startActivity(intent)
        }

        setRecyclerView(myToDoSet)
    }

    override fun onResume() {
        super.onResume()
        val binding = ActivityToDoBinding.inflate(layoutInflater)
        val title = setTitle()
        val content = setContent()
        presenter.addToDo(myToDoSet, title, content)
        binding.todoRecyclerView.adapter?.notifyDataSetChanged()
    }

    override fun setTitle(): String {
        val title = intent.getStringExtra(R.string.title.toString()).toString()
        Log.d(TAG2, title)
        return title
    }

    override fun setContent(): String {
        val content = intent.getStringExtra(R.string.content.toString()).toString()
        Log.d(TAG2, content)
        return content
    }

    override fun setRecyclerView(myToDoSet: List<MyToDoList>) {
        val binding = ActivityToDoBinding.inflate(layoutInflater)
        val recyclerView = binding.todoRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ToDoAdapter(myToDoSet)
    }
}