package com.example.diplom.recyclerAdapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.AdminHistoryActivity
import com.example.diplom.AdminReportInfo
import com.example.diplom.R
import com.example.diplom.models.ModelClassAdminHistory

class AdminHistoryRecyclerAdapter (private val history: List<ModelClassAdminHistory>?, private val activity: AdminHistoryActivity) : RecyclerView.Adapter<AdminHistoryRecyclerAdapter.MyViewHolder>() {
    //Создаю переменные к которым буду обращаться в адаптере
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var historyDate: TextView? = null
        var historyCabinet: TextView? = null
        var historyTypeRep: TextView? = null
        var historyStatus: TextView? = null
        var historyId: TextView? = null

        init {
            historyDate = itemView.findViewById(R.id.textViewDate)
            historyCabinet = itemView.findViewById(R.id.textViewCabinet)
            historyTypeRep = itemView.findViewById(R.id.textViewTypeRep)
            historyStatus = itemView.findViewById(R.id.textViewStatus)
            historyId = itemView.findViewById(R.id.textViewRepId)
        }
    }

    //идентификатор макета для отдельного элемента списка, созданный нами ранее
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_reports_history, parent, false)
        return MyViewHolder(itemView)
    }

    //связываем используемые текстовые метки с данными
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.historyDate?.text = history!![position].dateReport
        holder.historyCabinet?.text = "Кабинет ${history[position].cabinet}"
        holder.historyTypeRep?.text = history[position].reportType
        holder.historyId?.text ="№  ${history[position].id.toString()}"

        if (history[position].status == false){
            holder.historyStatus?.text = "На рассмотрении"
        }
        else{
            holder.historyStatus?.text = "Завершен"
        }

        holder.historyDate?.setOnClickListener {
            val intent : Intent = Intent(holder.itemView.context, AdminReportInfo::class.java)
            intent.putExtra("idReport", history[position].id)
            ContextCompat.startActivity(holder.itemView.context, intent, Bundle.EMPTY)
        }

        holder.historyCabinet?.setOnClickListener {
            val intent : Intent = Intent(holder.itemView.context, AdminReportInfo::class.java)
            intent.putExtra("idReport", history[position].id)
            ContextCompat.startActivity(holder.itemView.context, intent, Bundle.EMPTY)
        }

        holder.historyTypeRep?.setOnClickListener {
            val intent : Intent = Intent(holder.itemView.context, AdminReportInfo::class.java)
            intent.putExtra("idReport", history[position].id)
            ContextCompat.startActivity(holder.itemView.context, intent, Bundle.EMPTY)
        }

        holder.historyStatus?.setOnClickListener {
            val intent : Intent = Intent(holder.itemView.context, AdminReportInfo::class.java)
            intent.putExtra("idReport", history[position].id)
            ContextCompat.startActivity(holder.itemView.context, intent, Bundle.EMPTY)
        }
    }

    //Выводим наши переменные
    override fun getItemCount(): Int {
        return history!!.size
    }
}