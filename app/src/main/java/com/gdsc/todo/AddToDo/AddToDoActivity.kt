package com.gdsc.todo.AddToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.gdsc.todo.ToDo.ToDoActivity
import com.gdsc.todo.databinding.ActivityAddToDoBinding
import com.gdsc.todo.model.ListDatasource
import com.gdsc.todo.model.MyToDoList

class AddToDoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAddToDoBinding.inflate(layoutInflater)

        setSupportActionBar(binding.addTodoToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val myListSet = ListDatasource().loadMyToDoList()

        // 할 일 추가 버튼
        binding.addTodoButton.setOnClickListener {
            if(binding.addTodoTitle.text.isNotEmpty() && binding.addTodoContent.text.isNotEmpty()){
                myListSet.add(MyToDoList(binding.addTodoTitle.toString(), binding.addTodoContent.toString()))
                binding.addTodoTitle.setText("")
                binding.addTodoContent.setText("")
                Toast.makeText(this, "할 일이 추가되었습니다!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ToDoActivity::class.java)
                startActivity(intent)
            }

        }

        setContentView(binding.root)
    }

    // 툴바의 뒤로가기 버튼
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
}