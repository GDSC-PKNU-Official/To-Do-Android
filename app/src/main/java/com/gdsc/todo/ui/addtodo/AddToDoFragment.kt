package com.gdsc.todo.ui.addtodo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.gdsc.todo.R
import com.gdsc.todo.databinding.FragmentAddTodoBinding
import com.gdsc.todo.ui.ToDoViewModel
import com.gdsc.todo.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToDoFragment: BaseFragment<FragmentAddTodoBinding>(R.layout.fragment_add_todo) {

    private val viewModel: ToDoViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBinding()

        observeCompleteButton()
    }

    private fun setBinding() {
        binding.vm = viewModel
    }

    private fun observeCompleteButton() {
        viewModel.completeButtonClickEvent.observe(this) { clickEvent ->
            clickEvent.getContentIfNotHandled()?.let {
                navigateHomeActivity()
            }
        }
    }

    private fun navigateHomeActivity() {
        // TODO: 화면 전환

    }
}