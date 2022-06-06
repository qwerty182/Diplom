package com.example.diplom

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.diplom.api.RestApiManager
import kotlinx.android.synthetic.main.activity_teacher_cabinet_info.*
import java.util.*

class TeacherCabinetInfoActivity : AppCompatActivity() {

    private var idCabinet: Int = 0
    private val apiService = RestApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_cabinet_info)

        idCabinet = intent.getIntExtra("idCabinetTc", 0)

        Log.i("MyLog", idCabinet.toString())

        btnBookingCabinet.setOnClickListener {
            val intent = Intent(this@TeacherCabinetInfoActivity, TeacherBookingCabinetActivity::class.java)
            intent.putExtra("idCabinetBook", idCabinet)
            startActivity(intent)
        }

        apiService.cabinetInfoGet(idCab = idCabinet){
            textCabinetNumTc?.text = it!!.cabinetNumber
            textGeneralInfoTc?.text = it.cabinetInfo

            val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Base64.getDecoder().decode(it.cabinetImage)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            imageCabinetTc!!.setImageBitmap(
                BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))

            textBuildTc?.text = it.cabinetBuild
        }
    }
}