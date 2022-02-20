package com.gdsc.todo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.gdsc.todo.database.TodoDAO
import com.gdsc.todo.database.TodoDatabase
import com.gdsc.todo.model.TodoModel

class TodoRepository(application: Application) {
    private var todoDatabase: TodoDatabase = TodoDatabase.getInstance(application)
    private var todoDAO: TodoDAO = todoDatabase?.todoDAO()
    private var todoItems: LiveData<List<TodoModel>> = todoDAO.getTodoList ()

    fun getTodoList(): LiveData<List<TodoModel>> {
        return todoItems
    }

    fun insertTodo(todoModel: TodoModel) {
        todoDAO.insertTodo(todoModel)

        // Thread를 사용하는 이유는, 연산 시간이 오래걸리는 작업은 메인 쓰레드가 아닌 별도 쓰레드에서 하도록 하는게 좋다고 하네용!
        // 그렇지 않으면 런타임 크러시가 발생한다...라는데 이거는 제가 직접 다르게 한번 해봐야겠네용
//        Thread(Runnable {
//            todoDAO.insertTodo(todoModel)
//        }).start()
    }
}

