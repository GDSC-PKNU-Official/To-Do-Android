package com.gdsc.todo.model

/*
 ToDoList 아이템에 대한 데이터를 보유하고 있는 모델클래스입니다.
 ToDolist를 작성하는데 대표적으로 쓰이는 제목, 내용, 작성시간을 Data Class에 담았습니다.
 */

data class TodoModel(
    var title: String,
    var timeStamp: Long,
    var content: String
)