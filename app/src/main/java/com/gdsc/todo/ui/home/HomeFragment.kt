package com.gdsc.todo.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.gdsc.todo.R
import com.gdsc.todo.databinding.FragmentHomeBinding
import com.gdsc.todo.ui.ToDoViewModel
import com.gdsc.todo.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val toDoViewModel: ToDoViewModel by navGraphViewModels(R.id.homeFragment) { defaultViewModelProviderFactory }
    private val adapter: ToDoAdapter by lazy { ToDoAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHomeToDoContents.adapter = adapter
        binding.vm = toDoViewModel

        observeToDoList()
        observeAddFAB()
    }

    private fun observeToDoList() {
        toDoViewModel.toDoList.observe(this) { toDoList ->
            adapter.submitList(toDoList)
        }
    }

    private fun observeAddFAB() {
        toDoViewModel.addButtonClickEvent.observe(this) {
            navigateAddTodo()
        }
    }

    private fun navigateAddTodo() {
        val action = HomeFragmentDirections.toAddToDo()
        findNavController().navigate(action) // TODO: 부모 클래스에 선언해두자
    }
}