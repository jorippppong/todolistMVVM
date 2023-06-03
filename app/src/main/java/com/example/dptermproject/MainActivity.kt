package com.example.dptermproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dptermproject.todo.TodoActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //=================//
        //캘린더 변수 설정
        val calendarView = findViewById<MaterialCalendarView>(R.id.calendarView)

        //[캘린더] 현재 날짜로 설정
        calendarView.selectedDate = CalendarDay.today()

        //[캘린더] 선택한 날짜 가져 오기
        calendarView.setOnDateChangedListener { _, date, _ -> // 선택된 날짜 정보 가져 오기
            val year = date.year
            val month = date.month
            val dayOfMonth = date.day

            // 선택된 날짜 정보 출력
            //String selectedDate = year + "-" + month + "-" + dayOfMonth;
            //Log.d("mainActivity", "calendar selectedDate: " + selectedDate);

            //정보 가지고 다음 Activity 로 이동
            val intent = Intent(this@MainActivity, TodoActivity::class.java)
            intent.putExtra("year", year)
            intent.putExtra("month", month)
            intent.putExtra("day", dayOfMonth)
            startActivity(intent)
        }
    }
}