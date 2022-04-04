package com.gdsc.todo.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdsc.todo.databinding.TodoItemBinding
import com.gdsc.todo.dto.TodoModel

class TodoListAdapter(private val deleteItem: (TodoModel) -> Unit) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    var todoItems: List<TodoModel> = mutableListOf()

    inner class TodoViewHolder(private val todoItemBinding: TodoItemBinding) : RecyclerView.ViewHolder(todoItemBinding.root) {
        fun bind(todoModel: TodoModel) {
            todoItemBinding.tvTodoTitle.text = todoModel.title
            todoItemBinding.tvTodoTimeStamp.text = todoModel.timeStamp
            todoItemBinding.cbTodoIsChecked.isChecked = todoModel.isChecked
            todoItemBinding.btnTodoDelete.setOnClickListener {
                deleteItem(todoModel)
            }

            // 취소선 표시
            if (todoModel.isChecked) {
                todoItemBinding.tvTodoTitle.paintFlags = todoItemBinding.tvTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                todoItemBinding.tvTodoTitle.paintFlags = todoItemBinding.tvTodoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }

            todoItemBinding.cbTodoIsChecked.setOnClickListener {

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val todoItemBinding: TodoItemBinding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(todoItemBinding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoItems[position])
    }

    override fun getItemCount(): Int = todoItems.size

    // todolist 갱신!
    fun update(newItem: List<TodoModel>) {
        todoItems = newItem
        notifyDataSetChanged()
    }
}