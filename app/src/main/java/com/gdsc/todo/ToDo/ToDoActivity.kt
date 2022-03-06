package com.gdsc.todo.ToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdsc.todo.AddToDo.AddToDoActivity
import com.gdsc.todo.databinding.ActivityToDoBinding
import com.gdsc.todo.model.db.ToDoDatabase
import com.gdsc.todo.model.entity.MyToDoList
import java.util.*

const val TAG2 = "ToDoActivity"

class ToDoActivity : AppCompatActivity(), ToDoContract.View {
    // 사용자의 Input이 주어지면 뷰를 통해 Presenter로 흐름이 이어지므로
    // 뷰가 Presenter를 알고 있어야 한다.
    override lateinit var presenter: ToDoContract.Presenter
    private lateinit var myToDoSet: List<MyToDoList>
    private lateinit var recyclerView: RecyclerView
    private lateinit var toDoAdapter: ToDoAdapter
    private var db: ToDoDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = ToDoPresenter(this)
        recyclerView = binding.todoRecyclerView

        db = ToDoDatabase.getInstance(applicationContext) ?: throw IllegalAccessException()

        // 룸에 있는 데이터 불러오기
        getAllTodo()
        binding.mainAddButton.setOnClickListener {
            startAddToDoActivity()
        }
    }

    override fun setRecyclerView() {
        // 안드로이드 OS는 UI 자원 사용은 UI Thread에서만 가능하므로
        // Sub Thread에서 UI 자원을 다루기 위해 runOnUiThread를 사용
        runOnUiThread {
            toDoAdapter = ToDoAdapter(myToDoSet, db ?: throw IllegalAccessException())
            toDoAdapter.notifyDataSetChanged()
            recyclerView.apply {
                adapter = toDoAdapter
                setHasFixedSize(true)
            }
        }
    }

    override fun getAllTodo() {
        // 메인 쓰레드에서 Room DB 접근 시 에러가 발생하므로 백그라운드에서 작업해야 한다.
        Thread{
            myToDoSet = db!!.getToDoDao().getAll()
            // 리사이클러뷰 설정
            setRecyclerView()
        }.start()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onRestart() {
        super.onRestart()
        getAllTodo()
    }

    override fun onDestroy() {
        super.onDestroy()
        ToDoDatabase.destroyInstance()
        db = null
    }

    private fun startAddToDoActivity(){
        val intent = Intent(this, AddToDoActivity::class.java)
        startActivity(intent)
    }
}