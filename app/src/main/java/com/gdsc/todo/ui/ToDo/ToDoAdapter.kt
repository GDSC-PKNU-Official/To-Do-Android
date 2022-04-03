package com.gdsc.todo.ui.ToDo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdsc.todo.ui.ToDoViewModel
import com.gdsc.todo.databinding.ItemTodoBinding
import com.gdsc.todo.model.entity.MyToDoList

const val TAG = "ToDoAdapter"

class ToDoAdapter(
    private val myToDoSet: List<MyToDoList>,
    private val viewModel: ToDoViewModel
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

        // 체크박스 클릭 시 데이터 삭제
        fun checking(toDo: MyToDoList, viewModel: ToDoViewModel) {
            binding.checked.setOnCheckedChangeListener { _, _ ->
                Log.d(TAG, binding.checked.isChecked.toString())
                if (binding.checked.isChecked) {
                    Thread {
                        viewModel.deleteToDo(toDo)
                    }.start()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("size", myToDoSet.size.toString())
        if (myToDoSet.isNotEmpty()) {
            return myToDoSet.size
        }
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder =
        ToDoViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.binding.item = myToDoSet[position]
        holder.checking(myToDoSet[position], viewModel)
    }
}