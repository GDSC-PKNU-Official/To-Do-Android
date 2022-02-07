package com.gdsc.todo.AddToDo

import android.content.Intent
import android.util.Log
import com.gdsc.todo.R
import com.gdsc.todo.model.ListDatasource
import com.gdsc.todo.model.MyToDoList

class AddToDoPresenter(val addToDoView: AddToDoContract.View): AddToDoContract.Presenter {
    // Presenter가 연산이 끝나고 화면을 갱신해주기 위해서는 뷰를 알고 있어야 한다.
    private val myToDoSet = ListDatasource().loadMyToDoList()

    // 프리젠터 초기화
    init {
        addToDoView.presenter = this
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun saveToDo(title: String, content: String) {
        myToDoSet.add(MyToDoList(title, content))
        Log.d(TAG, myToDoSet[0].title)
        Log.d(TAG, myToDoSet[0].content)
    }

    override fun sendToDo(title: String, content: String, intent: Intent) {
        intent.putExtra(R.string.title.toString(), myToDoSet[0].title)
        intent.putExtra(R.string.content.toString(), myToDoSet[0].content)
    }
}