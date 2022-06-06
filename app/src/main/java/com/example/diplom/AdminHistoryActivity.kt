package com.example.diplom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.api.RestApiManager
import com.example.diplom.recyclerAdapter.AdminHistoryRecyclerAdapter
import kotlinx.android.synthetic.main.activity_admin_history.*

class AdminHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_history)

        val apiService = RestApiManager()
        apiService.reportAdminHistoryGet(){
            val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerViewHistoryAdmin.layoutManager = linearLayoutManager
            recyclerViewHistoryAdmin.adapter = AdminHistoryRecyclerAdapter(it, this@AdminHistoryActivity)
        }
    }
}