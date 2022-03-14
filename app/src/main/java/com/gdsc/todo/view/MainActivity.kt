package com.gdsc.todo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.get
import com.gdsc.todo.R
import com.gdsc.todo.databinding.ActivityMainBinding
import com.gdsc.todo.model.TodoModel
import com.gdsc.todo.view.adapter.TodoListAdapter
import com.gdsc.todo.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoListAdapter: TodoListAdapter
    private val todoItems: List<TodoModel> = listOf()  // 자료를 동적으로 변경할 수 있어서 ArrayList를 사용
    private var backKeyPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()   // 바인딩 초기화
        initAddButton() // todoList 추가하기 버튼
        requestActivity() // intent를 통해 다시 넘어올 데이터 처리를 담당
//        initViewModel() // 뷰모델 초기화
        initRecyclerView()  // 리사이클러뷰 초기화
    }

    // Main 뒤로가기 두 번 누르면 종료하기
    override fun onBackPressed() {
        if (System.currentTimeMillis() - backKeyPressed >= 2000) {
            backKeyPressed = System.currentTimeMillis()
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show()
        } else {
            exitProcess(0)  // 시스템 종료!
        }
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initAddButton() {
        binding.mainFAB.setOnClickListener {
            val intent = Intent(this, EditTodoActivity::class.java).apply {
                putExtra("TYPE", "ADD")
            }
            requestActivity().launch(intent)
//            startActivity(intent)
        }
    }

    /**
     * 현재의 한계점
     * 어플 시작과 동시에 getItem()이 실행되어서 없는데도 받아오려고 시도한다.
     * 차라리 EditTodoActivity에서 값 받아오지말고 바로 Insert를 시도해볼까 한다.
     * 일단 그건 나중에 추후 해보자!
     */
    private fun requestActivity(): ActivityResultLauncher<Intent> {
        val requestActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val todoTitle = it.data?.getStringExtra("editTitle")!!
                val todoTimeStamp = it.data?.getStringExtra("editDate")!!
                val TYPE = it.data?.getStringExtra("TYPE")
                val todoItem = TodoModel(0, todoTitle, todoTimeStamp, false)

                when (TYPE) {
                    "isADD" -> {
                        CoroutineScope(Dispatchers.IO).launch {
                            todoViewModel.insert(todoItem)
                        }
                    }

                }
            }
        }
        return requestActivity
    }


    private fun initRecyclerView() {
        // adapter에 click 시 해야할 일을 (todo) -> unit 파라미터로 넘겨준다.
        todoListAdapter = TodoListAdapter(todoItems) { todoModel -> deleteItem(todoModel) }
        binding.mainRV.apply {
            setHasFixedSize(true)
            adapter = todoListAdapter
        }
    }

    private fun deleteItem(todoModel: TodoModel) {
        todoViewModel.delete(todoModel)
    }


}


