package com.example.diplom.recyclerAdapter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.*
import com.example.diplom.api.RestApiManager
import com.example.diplom.models.ModelClassBookingList
import com.example.diplom.models.ModelClassBookingStatusEdit

class BookingRecyclerAdapter (private val book: List<ModelClassBookingList>?, private val activity: TeacherBookingHistoryActivity) : RecyclerView.Adapter<BookingRecyclerAdapter.MyViewHolder>(){
    //Создаю переменные к которым буду обращаться в адаптере
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var searchCabView: TextView? = null
        var searchTimeView: TextView? = null
        var searchDateView: TextView? = null
        var searchIdView: TextView? = null
        var searchButton: Button? = null

        init {
            searchCabView = itemView.findViewById(R.id.textViewBookCabinet)
            searchTimeView = itemView.findViewById(R.id.textViewBookTime)
            searchDateView = itemView.findViewById(R.id.textViewBookDate)
            searchIdView = itemView.findViewById(R.id.textViewBookId)
            searchButton = itemView.findViewById(R.id.btnCancelHis)
        }
    }

    //идентификатор макета для отдельного элемента списка, созданный нами ранее
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_booking, parent, false)
        return MyViewHolder(itemView)
    }

    //связываем используемые текстовые метки с данными
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.searchCabView?.text = "Кабинет ${book!![position].cabinetName}"
        holder.searchDateView?.text = book[position].bookingDate!!.substring(0, 10)
        holder.searchTimeView?.text = "Время начала пары: ${book[position].bookingTime}"
        holder.searchIdView?.text = "№${book[position].id}"

        holder.searchButton?.setOnClickListener {
            val apiService = RestApiManager()
            val modelClass = ModelClassBookingStatusEdit(
                idBookingAnOffice = book[position].id,
                bookingStatus = true)

            apiService.editBooking(modelClass) {
                if (it != null) {
                    Toast.makeText(holder.itemView.context, "Бронь отменина", Toast.LENGTH_LONG).show()
                    val intent = Intent(holder.itemView.context, TeacherInfoActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(holder.itemView.context, intent, Bundle.EMPTY)
                }
            }
        }
    }

    //Выводим наши переменные
    override fun getItemCount(): Int {
        return book!!.size
    }
}