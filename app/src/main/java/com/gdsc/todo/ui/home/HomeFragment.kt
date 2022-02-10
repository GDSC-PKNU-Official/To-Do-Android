package com.gdsc.todo.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.navGraphViewModels
import com.gdsc.todo.R
import com.gdsc.todo.databinding.ActivityHomeBinding
import com.gdsc.todo.databinding.FragmentHomeBinding
import com.gdsc.todo.ui.BaseActivity
import com.gdsc.todo.ui.ToDoViewModel
import com.gdsc.todo.ui.addtodo.AddToDoActivity
import com.gdsc.todo.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: ToDoViewModel by navGraphViewModels<>()
    private val adapter: ToDoAdapter by lazy { ToDoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvHomeToDoContents.adapter = adapter
        binding.vm = viewModel

        observeToDoList()
        observeAddFAB()
    }

    private fun observeToDoList() {
        viewModel.toDoList.observe(this) { toDoList ->
            adapter.submitList(toDoList)
        }
    }

    private fun observeAddFAB() {
        viewModel.addButtonClickEvent.observe(this) {
            navigateAddTodo()
        }
    }

    private fun navigateAddTodo() {
        val intent = Intent(this, AddToDoActivity::class.java)
        startActivity(intent)
    }
}