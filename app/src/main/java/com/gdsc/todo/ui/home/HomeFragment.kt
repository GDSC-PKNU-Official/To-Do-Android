package com.gdsc.todo.ui.home

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.gdsc.todo.R
import com.gdsc.todo.databinding.FragmentHomeBinding
import com.gdsc.todo.ui.ToDoViewModel
import com.gdsc.todo.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val toDoViewModel by navGraphViewModels<ToDoViewModel>(R.id.homeFragment) { defaultViewModelProviderFactory }
    private val adapter: ToDoAdapter by lazy { ToDoAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHomeToDoContents.adapter = adapter
        binding.vm = toDoViewModel

        setHasOptionsMenu(true)
        observeAddFAB()

        viewLifecycleOwner.lifecycleScope.launch {
            collectSortMenu()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            collectToDoList()
        }
    }

    private suspend fun collectToDoList() {
        toDoViewModel.getToDoList().collect { toDoList ->
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

    private suspend fun collectSortMenu() {
        toDoViewModel.sortMenuClickEvent.collect {
            toDoViewModel.getToDoListSortedByTitle().collect { toDoList ->
                adapter.submitList(toDoList)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.sort, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort -> toDoViewModel.clickSortMenu()
            else -> throw IllegalArgumentException(ERROR_NOT_EXIST_ID)
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ERROR_NOT_EXIST_ID = "없는 아이디"
    }
}