package com.gdsc.todo.ToDo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.gdsc.todo.databinding.ItemTodoBinding
import com.gdsc.todo.model.db.ToDoDatabase
import com.gdsc.todo.model.entity.MyToDoList

class ToDoAdapter(
    private val myToDoSet: List<MyToDoList>
): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root){

        // 체크박스 클릭 시 데이터 삭제
        fun checking(item: MyToDoList, db: ToDoDatabase){
            binding.checked.setOnCheckedChangeListener { _, _ ->
                Log.d("ToDoAdapter", binding.checked.isChecked.toString())
                if(binding.checked.isChecked){
                    Thread{
//                        db.getToDoDao().delete(item)
                    }.start()
                }
            }
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
        holder.binding.item = myToDoSet[position]
//        holder.checking(myToDoSet[position], db)
    }
}