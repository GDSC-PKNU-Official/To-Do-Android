package com.gdsc.todo.ToDo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.gdsc.todo.AddToDo.AddToDoActivity
import com.gdsc.todo.R
import com.gdsc.todo.databinding.ActivityToDoBinding
import com.gdsc.todo.model.ToDoDatabase
import com.gdsc.todo.model.local.ListDatasource
import com.gdsc.todo.model.entity.MyToDoList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TAG2 = "ToDoActivity"

class ToDoActivity : AppCompatActivity(), ToDoContract.View {
    // 사용자의 Input이 주어지면 뷰를 통해 Presenter로 흐름이 이어지므로
    // 뷰가 Presenter를 알고 있어야 한다.
    override lateinit var presenter: ToDoContract.Presenter
    private lateinit var myToDoSet: MutableList<MyToDoList>
    private lateinit var recyclerView: RecyclerView
    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var newToDo: MyToDoList
    private lateinit var db: ToDoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = ToDoPresenter(this)
        recyclerView = binding.todoRecyclerView
        // 룸에 있는 데이터 불러오기
        db = ToDoDatabase.getInstance(applicationContext)!!
        val r = Runnable{
            myToDoSet = db!!.toDoDao().getAll() as MutableList<MyToDoList>
            // room에서 불러온 데이터로 리사이클러뷰 만들기
            setRecyclerView(myToDoSet)
            recyclerView.adapter?.notifyDataSetChanged()
        }
        val thread = Thread(r)
        thread.start()

        binding.mainAddButton.setOnClickListener {
            val intent = Intent(this, AddToDoActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
    }

    override fun setRecyclerView(myToDoSet: List<MyToDoList>) {
        toDoAdapter = ToDoAdapter(myToDoSet)
        recyclerView.apply{
            adapter = toDoAdapter
        }
    }
}