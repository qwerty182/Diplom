package com.example.diplom

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.example.diplom.api.RestApiManager
import com.example.diplom.models.ModelClassReportItem
import kotlinx.android.synthetic.main.activity_student_report_add.*
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class StudentReportAddActivity : AppCompatActivity() {

    private var idCabinetReport: Int = 0
    private var idReportType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_report_add)

        idCabinetReport = intent.getIntExtra("idCabinetReport", 0)

        imageReport.setOnClickListener{
            val imageTakeIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(imageTakeIntent,1)
        }

        btnSave.setOnClickListener {
            addReport()
        }

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.typeReport, R.layout.spinner_item_reg)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_reg)
        spinnerTypeReport.setAdapter(adapter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val extras = data?.extras
            val bitmap = extras?.get("data") as Bitmap?
            imageReport.setImageBitmap(bitmap)
        }
    }

    private fun addReport(){
        val date = getCurrentDateTime()
        val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")

        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val idUser = sharedPreferences.getInt("IDUSER", 0)

        if (spinnerTypeReport.selectedItem == "Поломка"){
            idReportType = 1
        }else if(spinnerTypeReport.selectedItem == "Забыли вещь"){
            idReportType = 2
        }else if(spinnerTypeReport.selectedItem == "Кража"){
            idReportType = 3
        }

        if (editComment.text.isNullOrEmpty()) {
            Toast.makeText(applicationContext, "Ошибка! Не все поля заполнены", Toast.LENGTH_LONG).show()
            return
        }
        val apiService = RestApiManager()
        val save = BitmapToByteArray().toBase64()
        val modelClass = ModelClassReportItem(
            comment = editComment.text.toString(),
            images = save,
            dateOfLocations = dateInString,
            status = false,
            userId = idUser,
            cabinetId = idCabinetReport,
            reportTypeId = idReportType)

        Log.i("MyLog", modelClass.userId.toString())
        Log.i("MyLog", modelClass.images)
        Log.i("MyLog", BitmapToByteArray().toBase64())

        apiService.addReports(modelClass) {
            if (it != null) {
                Toast.makeText(applicationContext, "Отчет добавлен", Toast.LENGTH_LONG).show()
                val intent = Intent(this@StudentReportAddActivity, StudentCabinetActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun BitmapToByteArray(): ByteArray
    {
        val stream = ByteArrayOutputStream()
        imageReport.drawable.toBitmap()!!.compress(Bitmap.CompressFormat.JPEG,
            100, stream)
        val bitmapdata: ByteArray = stream.toByteArray()
        return bitmapdata
    }
    private fun ByteArray.toBase64(): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String(Base64.getEncoder().encode(this))
        } else {
            TODO("VERSION.SDK_INT < O")
        }


    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}