package com.example.diplom

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.api.RestApiManager
import com.example.diplom.recyclerAdapter.BookingRecyclerAdapter
import kotlinx.android.synthetic.main.activity_teacher_booking_history.*

class TeacherBookingHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_booking_history)

        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val idUser = sharedPreferences.getInt("IDUSERTEACHER", 4)

        Log.i("MyLog", idUser.toString())

        val apiService = RestApiManager()
        apiService.bookingListGet(idBooking = idUser){
            val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerViewBooking.layoutManager = linearLayoutManager
            recyclerViewBooking.adapter = BookingRecyclerAdapter(it, this@TeacherBookingHistoryActivity)
        }
    }
}