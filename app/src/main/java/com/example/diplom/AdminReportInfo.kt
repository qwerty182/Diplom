package com.example.diplom

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.diplom.api.RestApiManager
import com.example.diplom.models.ModelClassReportsEdit
import kotlinx.android.synthetic.main.activity_admin_report_info.*
import java.util.*

class AdminReportInfo : AppCompatActivity() {

    private var idReport: Int = 0
    private val apiService = RestApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_report_info)

        idReport = intent.getIntExtra("idReport", 0)

        Log.i("MyLog", idReport.toString())

        btnStatus.setOnClickListener {
            editReport()
        }

        apiService.reportInfoGet(idRep = idReport){
            textLoginInfo?.text = "${it!!.userLast} ${it.userName} ${it.userMiddle}"
            textTypeReportInfo?.text = it.nameType

            val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Base64.getDecoder().decode(it.imageReport)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            imageReportInfo!!.setImageBitmap(
                BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))

            textCommentInfo?.text = it.commentReport
            textDateInfo?.text = it.dateReport
            textCabinetInfo?.text = "${it.build} ${it.cabinetName}"
        }
    }

    private fun editReport(){

        val modelClass = ModelClassReportsEdit(
            idReport = idReport,
            status = true)

        apiService.editReports(modelClass) {
            if (it != null) {
                Toast.makeText(applicationContext, "Отчет иправлен", Toast.LENGTH_LONG).show()
                val intent = Intent(this@AdminReportInfo, AdminCabinetActivity::class.java)
                startActivity(intent)
            }
        }
    }
}