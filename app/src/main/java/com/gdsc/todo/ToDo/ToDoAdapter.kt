package com.gdsc.todo.ToDo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdsc.todo.databinding.ItemTodoBinding
import com.gdsc.todo.model.MyToDoList

class ToDoAdapter(
    private val myToDoSet: List<MyToDoList>
): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int{
        Log.d("size", myToDoSet.size.toString())
        return myToDoSet.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder =
        ToDoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val binding = holder.binding
        binding.title.text = myToDoSet[position].title
        binding.content.text = myToDoSet[position].content
    }

}