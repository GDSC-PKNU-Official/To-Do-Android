package com.gdsc.todo.ToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdsc.todo.AddToDo.AddToDoActivity
import com.gdsc.todo.AddToDo.AddToDoContract
import com.gdsc.todo.AddToDo.AddToDoPresenter
import com.gdsc.todo.AddToDo.TAG
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = ToDoPresenter(this)
        val myToDoSet = ListDatasource().loadMyToDoList()

        binding.mainAddButton.setOnClickListener {
            val intent = Intent(this, AddToDoActivity::class.java)
            startActivity(intent)
        }

        val title = intent.getStringExtra(R.string.title.toString()).toString()
        val content = intent.getStringExtra(R.string.content.toString()).toString()
        Log.d(TAG2, title)
        Log.d(TAG2, content)
        myToDoSet.add(MyToDoList(title, content))

        setRecyclerView(myToDoSet)
    }

    override fun onResume() {
        super.onResume()
        val binding = ActivityToDoBinding.inflate(layoutInflater)
        binding.todoRecyclerView.adapter?.notifyDataSetChanged()
    }

    override fun setRecyclerView(myToDoSet: List<MyToDoList>) {
        val binding = ActivityToDoBinding.inflate(layoutInflater)
        val recyclerView = binding.todoRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ToDoAdapter(myToDoSet)
    }
}