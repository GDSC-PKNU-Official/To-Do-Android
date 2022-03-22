package com.gdsc.todo.ui.AddToDo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gdsc.todo.R
import com.gdsc.todo.ui.ToDo.ToDoActivity
import com.gdsc.todo.ui.ToDoViewModel
import com.gdsc.todo.databinding.ActivityAddToDoBinding
import com.gdsc.todo.model.db.ToDoDatabase

const val TAG = "AddToDoActivity"

class AddToDoActivity : AppCompatActivity() {
    private lateinit var viewModel: ToDoViewModel
    private lateinit var binding: ActivityAddToDoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_to_do)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory(getApplication())).get(
            ToDoViewModel::class.java)
        binding.addToDoViewModel = viewModel

        // 뒤로가기 버튼 생성
        setSupportActionBar(binding.addTodoToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 할 일 추가 버튼
        // 데이터바인딩으로 xml 파일을 통해 액티비티를 이동하는 방법을 찾지 못해 다음과 같이 작성
        binding.addTodoButton.setOnClickListener {
            Log.d(TAG, "addButtonClick")
            addAfterCheck()
        }
    }

    // 툴바의 뒤로가기 버튼 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            android.R.id.home -> {
                startToDoActivity()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showEmptyToDoError() {
        Toast.makeText(this, getString(R.string.show_empty), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        ToDoDatabase.destroyInstance()
        super.onDestroy()
    }

    private fun startToDoActivity() {
        val intent = Intent(this, ToDoActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun addAfterCheck()= when(viewModel.checkEmpty()){
        true -> {
            viewModel.addButtonClick()
            startToDoActivity()
        } else -> {
            showEmptyToDoError()
        }
    }
}