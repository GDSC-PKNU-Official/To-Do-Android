package com.gdsc.todo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdsc.todo.R
import com.gdsc.todo.model.TodoModel

/*
    model의 TodoModel 리스트를 생성자로부터 전달받아 RecyclerView.Adapter를 상속받고,
    RecyclerView.ViewHolder를 뷰홀더로 갖는 클래스를 구현합니다.
 */
class TodoListAdapter(val todoItems: ArrayList<TodoModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(todoModel: TodoModel) {}
    }

    // 이 Adapter가 todoItems를 얼마나 가지고 있는지 나타내는 함수입니다.
    // 즉, RecyclerView에 표시해야할 아이템의 갯수를 나타냅니다.
    // 코틀린스럽게(?) 코드를 작성해보았습니다.
    override fun getItemCount(): Int = todoItems.size

    // 현재 이 아이템이 사용할 뷰홀더를 생성하여 반환한다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item_list, parent, false)
        return TodoViewHolder(view)
    }

    // 현재 아이템의 포지션에 대한 데이터 모델을 리스트에서 얻고 Holder객체를 TodoViewHolder로 형변환한뒤
    // bind 메서드에 이 모델을 전달하여 데이터를 바인딩하도록 합니다.
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val todoModel = todoItems[position]
        val todoViewHolder = holder as TodoViewHolder

        todoViewHolder.bind(todoModel)
    }
}