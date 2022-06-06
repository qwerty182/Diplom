package com.example.diplom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.api.RestApiManager
import com.example.diplom.recyclerAdapter.ReportsRecyclerAdapter
import kotlinx.android.synthetic.main.activity_admin_view_reports.*
class AdminViewReportsActivity : AppCompatActivity() {

    private var idCabinetReport: Int = 0
    private val apiService = RestApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_view_reports)

        idCabinetReport = intent.getIntExtra("idCabinetReport", 0)

        apiService.reportListGet(idCabinet = idCabinetReport){
            val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = ReportsRecyclerAdapter(it, this@AdminViewReportsActivity)
        }
    }
}