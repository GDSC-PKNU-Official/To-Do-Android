package com.gdsc.todo.AddToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.gdsc.todo.R
import com.gdsc.todo.ToDo.ToDoActivity
import com.gdsc.todo.databinding.ActivityAddToDoBinding
import com.gdsc.todo.model.db.ToDoDatabase
import com.gdsc.todo.model.entity.MyToDoList
import java.lang.Thread.sleep

const val TAG = "AddToDoActivity"

class AddToDoActivity : AppCompatActivity(), AddToDoContract.View {
    override lateinit var presenter: AddToDoContract.Presenter
    private lateinit var title: TextView
    private lateinit var content: TextView
    private var db: ToDoDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = binding.addTodoTitle
        content = binding.addTodoContent
        presenter = AddToDoPresenter(this)
        db = ToDoDatabase.getInstance(applicationContext) ?: throw IllegalAccessException()

        // 뒤로가기 버튼 생성
        setSupportActionBar(binding.addTodoToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 할 일 추가 버튼
        binding.addTodoButton.setOnClickListener {
            if(getTitlee()!=null  && getContent()!=null){
                (presenter as AddToDoPresenter).saveToDo(db ?: throw IllegalAccessException(), getTitlee().toString(), getContent().toString())
                setNull()
                startToDoActivity()
            } else{
                showEmptyToDoError()
            }
        }
    }

    // 툴바의 뒤로가기 버튼 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showEmptyToDoError() {
        Toast.makeText(this, getString(R.string.show_empty), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        ToDoDatabase.destroyInstance()
        super.onDestroy()
    }

    override fun getTitlee(): String? {
        return title.text.toString()
    }

    override fun getContent(): String? {
        return content.text.toString()
    }

    override fun startToDoActivity() {
        val intent = Intent(this, ToDoActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun setNull() {
        title.text = null
        content.text = null
    }
}