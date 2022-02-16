package com.gdsc.todo.ToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var myToDoSet: MutableList<MyToDoList>
    private lateinit var recyclerView: RecyclerView
    private lateinit var toDoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = ToDoPresenter(this)
        myToDoSet = ListDatasource().loadMyToDoList()
        recyclerView = binding.todoRecyclerView

        binding.mainAddButton.setOnClickListener {
            val intent = Intent(this, AddToDoActivity::class.java)
            startActivity(intent)
        }
        setRecyclerView(myToDoSet)

        if(getTitlee()!=null && getContent()!=null){
            myToDoSet.add(MyToDoList(getTitlee().toString(), getContent().toString()))
            toDoAdapter?.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
//        presenter.addToDo(myToDoSet, getTitlee().toString(), getContent().toString())
//        myToDoSet.add(MyToDoList(getTitlee().toString(), getContent().toString()))
//        toDoAdapter?.notifyDataSetChanged()
    }

    override fun getTitlee(): String? {
        val title = intent.getStringExtra(R.string.title.toString()).toString()
        Log.d(TAG2, title)
        if(title.isNotEmpty()) return title
        else return null
    }

    override fun getContent(): String? {
        val content = intent.getStringExtra(R.string.content.toString()).toString()
        Log.d(TAG2, content)
        if(content.isNotEmpty()) return content
        else return null
    }

    override fun setRecyclerView(myToDoSet: List<MyToDoList>) {
        toDoAdapter = ToDoAdapter(myToDoSet)
        recyclerView.apply{
            adapter = toDoAdapter
        }
    }
}