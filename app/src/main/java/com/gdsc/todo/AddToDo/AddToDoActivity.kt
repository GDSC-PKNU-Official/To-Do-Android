package com.gdsc.todo.AddToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.gdsc.todo.ToDo.ToDoActivity
import com.gdsc.todo.databinding.ActivityAddToDoBinding
import com.gdsc.todo.model.ListDatasource
import com.gdsc.todo.model.MyToDoList

class AddToDoActivity : AppCompatActivity() {
    val myListSet = ListDatasource().loadMyToDoList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAddToDoBinding.inflate(layoutInflater)

        setSupportActionBar(binding.addTodoToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // 할 일 추가 버튼
        binding.addTodoButton.setOnClickListener {
            if(binding.addTodoTitle.text.isNotEmpty() && binding.addTodoContent.text.isNotEmpty()){
                myListSet.add(MyToDoList(binding.addTodoTitle.getText().toString(), binding.addTodoContent.getText().toString()))
                binding.addTodoTitle.setText("")
                binding.addTodoContent.setText("")
                Toast.makeText(this, "할 일이 추가되었습니다!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ToDoActivity::class.java)

//                Log.d("추가된 리스트 title", myListSet[0].title)
//                Log.d("추가된 리스트 content", myListSet[0].content)

                intent.putExtra("타이틀", myListSet[0].title)
                intent.putExtra("컨텐트", myListSet[0].content)
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