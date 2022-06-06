package com.example.diplom

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.api.RestApiManager
import com.example.diplom.recyclerAdapter.StudentHistoryRecyclerAdapter
import kotlinx.android.synthetic.main.activity_student_report_history.*

class StudentReportHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_report_history)

        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val idUser = sharedPreferences.getInt("IDUSER", 0)

        Log.i("MyLog", idUser.toString())

        val apiService = RestApiManager()
        apiService.reportStudentHistoryGet(idHistory = idUser){
            val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerViewHistoryUser.layoutManager = linearLayoutManager
            recyclerViewHistoryUser.adapter = StudentHistoryRecyclerAdapter(it, this@StudentReportHistoryActivity)
        }
    }
}