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
    class ToDoViewHolder(private val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: MyToDoList) {
            binding.title.text = item.title
            binding.content.text = item.content
        }
    }

    override fun getItemCount(): Int{
        Log.d("size", myToDoSet.size.toString())
        if(myToDoSet.isNotEmpty()){
            return myToDoSet.size
        }
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder =
        ToDoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(myToDoSet[position])
    }

}