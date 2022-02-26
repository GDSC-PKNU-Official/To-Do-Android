package com.gdsc.todo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdsc.todo.databinding.TodoItemListBinding
import com.gdsc.todo.model.TodoModel
import com.gdsc.todo.viewmodel.TodoViewModel


//  model의 TodoModel 리스트를 생성자로부터 전달받아 RecyclerView.Adapter를 상속받고,
//  RecyclerView.ViewHolder를 뷰홀더로 갖는 클래스를 구현합니다.
class TodoListAdapter(todoItems: ArrayList<TodoModel>) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    private var todoItems: List<TodoModel> = listOf()

    class TodoViewHolder(private val binding: TodoItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var todoModel: TodoModel
        lateinit var todoViewModel: TodoViewModel

        fun bind(todoModel: TodoModel, todoViewModel: TodoViewModel){
//            binding.todo

            binding.todoItemListTitleTV.text = todoModel.title
            binding.todoItemListTimeStampTV.text = todoModel.timeStamp
            binding.todoItemListCheckCB.isChecked = todoModel.checked
        }
    }

    override fun getItemCount(): Int = todoItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val viewHolder = TodoItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoItems[position])
    }

    fun setTodoItems(todoItems: ArrayList<TodoModel>){
        this.todoItems = todoItems
        notifyDataSetChanged()
    }
}