package com.example.diplom

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.diplom.api.RestApiManager
import com.example.diplom.models.ModelClassBookingClass
import kotlinx.android.synthetic.main.activity_teacher_booking_cabinet.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TeacherBookingCabinetActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener  {

    private var idCabinetBook: Int = 0
    private var idTime: Int = 0
    private val apiService = RestApiManager()

    private var day = 0
    private var month = 0
    private var year = 0

    private var saveDay = 0
    private var saveMonth = 0
    private var saveYear = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_booking_cabinet)

        pickDate()

        idCabinetBook = intent.getIntExtra("idCabinetBook", 0)

        Log.i("MyLog", idCabinetBook.toString())

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.time, R.layout.spinner_item_reg)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_reg)
        spinnerTime.setAdapter(adapter)

        apiService.cabinetInfoGet(idCab = idCabinetBook){
            textCabinetBook?.text = "Кабинет ${it!!.cabinetNumber}"

            val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Base64.getDecoder().decode(it.cabinetImage)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            imageCabinetBook!!.setImageBitmap(BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))
        }

        btnBook.setOnClickListener {
            addBooking()
        }
    }

    private fun getDateTimeCalendar(){
        val cal:Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    private fun pickDate(){
        textTitleDate.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(this,this, year,month,day).show()
        }
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        saveDay = dayOfMonth
        saveMonth = month + 1
        saveYear = year

        var saveDayS: String = saveDay.toString()
        var saveMonthS: String = saveMonth.toString()

        getDateTimeCalendar()
        if (saveDay < 10){
            saveDayS = "0${saveDay}"
        }
        if (saveMonth < 10){
            saveMonthS = "0${saveMonth}"
        }
        textTitleDate.text = "$saveYear-$saveMonthS-${saveDayS}"
    }

    private fun addBooking(){
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("IDUSERTEACHER", 0)

        val currentDate = Date()
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val timeText: String = timeFormat.format(currentDate)

        if (spinnerTime.selectedItem == "8:30"){
            idTime = 1
        }else if(spinnerTime.selectedItem == "10:10"){
            idTime = 2
        }else if(spinnerTime.selectedItem == "12:00"){
            idTime = 3
        }else if(spinnerTime.selectedItem == "14:00"){
            idTime = 4
        }else if(spinnerTime.selectedItem == "15:40"){
            idTime = 5
        }

        if (textTitleDate.text == "Дата пары") {
            Toast.makeText(applicationContext, "Ошибка! Не все поля заполнены", Toast.LENGTH_LONG).show()
            return
        }

        val modelClass = ModelClassBookingClass(
            bookingDate = "${textTitleDate.text}T${timeText}",
            bookingStatus = false,
            userId = userId,
            bookingTimeId = idTime,
            cabinetId = idCabinetBook)

        apiService.bookClass(modelClass) {
            if (it != null) {
                Toast.makeText(applicationContext, "Бронь добавлена", Toast.LENGTH_LONG).show()
                val intent = Intent(this@TeacherBookingCabinetActivity, TeacherCabinetActivity::class.java)
                startActivity(intent)
            }
        }
    }
}