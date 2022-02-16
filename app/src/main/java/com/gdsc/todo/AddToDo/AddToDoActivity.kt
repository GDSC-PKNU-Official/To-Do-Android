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
import com.gdsc.todo.model.ListDatasource
import com.gdsc.todo.model.MyToDoList

const val TAG = "AddToDoActivity"

class AddToDoActivity : AppCompatActivity(), AddToDoContract.View {
    override lateinit var presenter: AddToDoContract.Presenter
    private lateinit var title: TextView
    private lateinit var content: TextView
    private lateinit var myToDoSet: MutableList<MyToDoList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = binding.addTodoTitle
        content = binding.addTodoContent

        presenter = AddToDoPresenter(this)
        myToDoSet = ListDatasource().loadMyToDoList()

        setSupportActionBar(binding.addTodoToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 할 일 추가 버튼
        binding.addTodoButton.setOnClickListener {
            if(title.text.isNotEmpty() && content.text.isNotEmpty()){
                presenter.saveToDo(myToDoSet, title.text.toString(), content.text.toString())
                // myToDoSet.add(MyToDoList(title.text.toString(), content.text.toString()))
                val intent = Intent(this, ToDoActivity::class.java)
                presenter.sendToDo(title.text.toString(), content.text.toString(), intent)
                title.text = null
                content.text = null
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