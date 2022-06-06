package com.example.diplom

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diplom.api.RestApiManager
import kotlinx.android.synthetic.main.activity_student_detail_cabinet.*
import java.util.*

class StudentDetailCabinetActivity : AppCompatActivity() {

    private var idCabinet: Int = 0
    private val apiService = RestApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail_cabinet)

        idCabinet = intent.getIntExtra("idCabinet", 0)

        btnAddReport.setOnClickListener {
            val intent = Intent(this@StudentDetailCabinetActivity, StudentReportAddActivity::class.java)
            intent.putExtra("idCabinetReport", idCabinet)
            startActivity(intent)
        }

        apiService.cabinetInfoGet(idCab = idCabinet){
            textCabinetNum?.text = it!!.cabinetNumber
            textGeneralInfo?.text = it.cabinetInfo

            val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Base64.getDecoder().decode(it.cabinetImage)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            imageCabinet!!.setImageBitmap(
                BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))

            textBuild?.text = it.cabinetBuild
        }
    }
}