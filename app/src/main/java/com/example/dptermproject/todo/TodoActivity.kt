package com.example.dptermproject.todo

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dptermproject.convertMonthToString
import com.example.dptermproject.databinding.ActivityTodoBinding

class TodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoBinding
    private val viewModel:TodoViewModel by viewModels()

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
        binding.recyclerView.adapter = TodoAdapter(
            emptyList(),
            onClickDeleteIcon = {
                viewModel.deleteTask(it)
            }
        )

        //[투두] 투두 추가
        binding.btnTodo.setOnClickListener {
            val todo = Todo("", isDone = false, modify = true)
            viewModel.addTask(todo)
        }

        //관찰 UI Update
        viewModel.todoLiveData.observe(this, Observer {
            (binding.recyclerView.adapter as TodoAdapter).setData(it)
        })
    }


}