package com.gdsc.todo.AddToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.gdsc.todo.R
import com.gdsc.todo.ToDo.ToDoActivity
import com.gdsc.todo.databinding.ActivityAddToDoBinding

const val TAG = "AddToDoActivity"

class AddToDoActivity : AppCompatActivity(), AddToDoContract.View {
    override lateinit var presenter: AddToDoContract.Presenter
    private lateinit var title: TextView
    private lateinit var content: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = binding.addTodoTitle
        content = binding.addTodoContent

        presenter = AddToDoPresenter(this)

        setSupportActionBar(binding.addTodoToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 할 일 추가 버튼
        binding.addTodoButton.setOnClickListener {
            if(title.text.isNotEmpty() && content.text.isNotEmpty()){
                presenter.saveToDo(title.text.toString(), content.text.toString())
                title.text = ""
                content.text = ""
                val intent = Intent(this, ToDoActivity::class.java)
                presenter.sendToDo(title.text.toString(), content.text.toString(), intent)
                startActivity(intent)
            } else{
                showEmptyToDoError()
            }
        }
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

    override fun showEmptyToDoError() {
        Toast.makeText(this, getString(R.string.show_empty), Toast.LENGTH_SHORT).show()
    }
}