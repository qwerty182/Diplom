package com.example.diplom

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diplom.api.RestApiManager
import kotlinx.android.synthetic.main.activity_admin_cabinet_info.*
import java.util.*

class AdminCabinetInfoActivity : AppCompatActivity() {

    private var idCabinet: Int = 0
    private val apiService = RestApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_cabinet_info)

        idCabinet = intent.getIntExtra("idCabinet", 0)

        btnEditCabinet.setOnClickListener {
            val intent = Intent(this@AdminCabinetInfoActivity, AdminEditCabinetActivity::class.java)
            intent.putExtra("idCabinetEdit", idCabinet)
            startActivity(intent)
        }

        btnViewReport.setOnClickListener {
            val intent = Intent(this@AdminCabinetInfoActivity, AdminViewReportsActivity::class.java)
            intent.putExtra("idCabinetReport", idCabinet)
            startActivity(intent)
        }

        apiService.cabinetInfoGet(idCab = idCabinet){
            textCabinetNumAd?.text = it!!.cabinetNumber
            textGeneralInfoAd?.text = it.cabinetInfo

            val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Base64.getDecoder().decode(it.cabinetImage)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            imageCabinetAd!!.setImageBitmap(
                BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))

            textBuildAd?.text = it.cabinetBuild
        }
    }
}