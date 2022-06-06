package com.example.diplom

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.diplom.api.RestApiManager
import kotlinx.android.synthetic.main.activity_student_history_info.*
import java.util.*

class StudentHistoryInfoActivity : AppCompatActivity() {

    private var idRep: Int = 0
    private val apiService = RestApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_history_info)

        idRep = intent.getIntExtra("idRep", 0)

        Log.i("MyLog", idRep.toString())

        apiService.reportInfoGet(idRep = idRep){
            textIdRep?.text = "№${it!!.idReport}"
            textTypeReportInfo.text = it.nameType

            val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Base64.getDecoder().decode(it.imageReport)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            imageReportInfo!!.setImageBitmap(
                BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))

            textCommentInfo?.text = it.commentReport
            textCabinetInfo?.text = "${it.build} ${it.cabinetName}"
            textDateInfo?.text = it.dateReport

        }
    }
}