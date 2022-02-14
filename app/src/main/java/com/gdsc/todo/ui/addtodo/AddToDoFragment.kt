package com.gdsc.todo.ui.addtodo

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.gdsc.todo.R
import com.gdsc.todo.databinding.FragmentAddTodoBinding
import com.gdsc.todo.ui.ToDoViewModel
import com.gdsc.todo.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToDoFragment: BaseFragment<FragmentAddTodoBinding>(R.layout.fragment_add_todo) {

    private val viewModel by navGraphViewModels<ToDoViewModel>(R.id.addToDoFragment) { defaultViewModelProviderFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBinding()

        observeCompleteButton()
    }

    private fun setBinding() {
        binding.vm = viewModel
    }

    private fun observeCompleteButton() {
        viewModel.completeButtonClickEvent.observe(requireActivity()) { clickEvent ->
            clickEvent.getContentIfNotHandled()?.let {
                navigateHomeActivity()
            }
        }
    }

    private fun navigateHomeActivity() {
        val action = AddToDoFragmentDirections.toHome()
        findNavController().navigate(action)
    }
}