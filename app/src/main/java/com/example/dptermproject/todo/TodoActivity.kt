package com.example.dptermproject.todo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dptermproject.convertMonthToString
import com.example.dptermproject.databinding.ActivityTodoBinding

class TodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoBinding
    private val data = arrayListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** 날짜 **/
        val intent = intent
        val year = intent.getIntExtra("year", 0).toString()
        val month = intent.getIntExtra("month", 0)
        val day = intent.getIntExtra("day", 0).toString()
        Log.d("yuri", "$year-$month-$day")
        binding.year.text = year
        binding.month.text = convertMonthToString(month)
        binding.day.text = day

        /** 투두 **/
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = TodoAdapter(data,
            onClickDeleteIcon = {
                deleteTask(it)
            }
        )

        //[투두] 투두 추가
        binding.btnTodo.setOnClickListener {
            addTask()
        }
    }

    //투두 삭제
    private fun deleteTask(todo: Todo) {
        data.remove(todo)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    //투두 추가
    private fun addTask() {
        val todo = Todo("", isDone = false, modify = true)
        data.add(todo)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}