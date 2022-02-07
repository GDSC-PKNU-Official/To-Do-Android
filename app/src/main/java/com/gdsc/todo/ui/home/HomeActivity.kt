package com.gdsc.todo.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.gdsc.todo.R
import com.gdsc.todo.databinding.ActivityHomeBinding
import com.gdsc.todo.ui.BaseActivity
import com.gdsc.todo.ui.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val viewModel: ToDoViewModel by viewModels()
    private val adapter = ToDoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvHomeToDoContents.adapter = adapter

        // TODO: To Do List의 변화가 생기는지 observe
//        viewModel.toDoList.observe(this) {
//            adapter.submitList(this)
//        }
    }
}