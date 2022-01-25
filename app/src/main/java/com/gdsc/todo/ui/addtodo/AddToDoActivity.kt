package com.gdsc.todo.ui.addtodo

import android.os.Bundle
import androidx.activity.viewModels
import com.gdsc.todo.ui.BaseActivity
import com.gdsc.todo.R
import com.gdsc.todo.databinding.ActivityAddTodoBinding
import com.gdsc.todo.ui.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToDoActivity: BaseActivity<ActivityAddTodoBinding>(R.layout.activity_add_todo) {

    private val viewModel: ToDoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        setObserve()
    }

    private fun setObserve() {
        viewModel.addButtonClickEvent.observe(this) {
            finish()
        }
    }
}