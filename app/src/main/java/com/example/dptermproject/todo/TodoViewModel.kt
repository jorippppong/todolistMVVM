package com.example.dptermproject.todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel: ViewModel() {
    val todoLiveData = MutableLiveData<List<Todo>>()
    private val data = arrayListOf<Todo>()

    //??
    fun toggleTodo(todo:Todo){
        todo.isDone = !todo.isDone
        todoLiveData.value = data
    }

    //투두 삭제
    fun deleteTask(todo: Todo) {
        data.remove(todo)
        todoLiveData.value = data
    }

    //투두 추가
    fun addTask(todo:Todo) {
        data.add(todo)
        todoLiveData.value = data
    }

}