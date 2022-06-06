package com.example.diplom.recyclerAdapter

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.AdminReportInfo
import com.example.diplom.AdminViewReportsActivity
import com.example.diplom.R
import com.example.diplom.models.ModelClassReportsList
import java.util.*

class ReportsRecyclerAdapter (private val report: List<ModelClassReportsList>?, private val activity: AdminViewReportsActivity) : RecyclerView.Adapter<ReportsRecyclerAdapter.MyViewHolder>() {

    //Создаю переменные к которым буду обращаться в адаптере
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var reportType: TextView? = null
        var reportDate: TextView? = null
        var reportUser: TextView? = null
        var reportId: TextView? = null

        init {
            reportType = itemView.findViewById(R.id.textViewTypeCabinet)
            reportDate = itemView.findViewById(R.id.textViewDateCabinet)
            reportUser = itemView.findViewById(R.id.textViewUserCabinet)
            reportId = itemView.findViewById(R.id.textViewRepIdCabinet)
        }
    }

    //идентификатор макета для отдельного элемента списка, созданный нами ранее
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_reports, parent, false)
        return MyViewHolder(itemView)
    }

    //связываем используемые текстовые метки с данными
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.reportType?.text = report!![position].reportType
        holder.reportDate?.text = report[position].dateReport
        holder.reportUser?.text = "${report[position].userLast} ${report[position].userName} ${report[position].userMiddle}"
        holder.reportId?.text = "№${report[position].id}"

        holder.reportType?.setOnClickListener {
            val intent : Intent = Intent(holder.itemView.context, AdminReportInfo::class.java)
            intent.putExtra("idReport", report[position].id)
            ContextCompat.startActivity(holder.itemView.context, intent, Bundle.EMPTY)
        }
        holder.reportDate?.setOnClickListener {
            val intent : Intent = Intent(holder.itemView.context, AdminReportInfo::class.java)
            intent.putExtra("idReport", report[position].id)
            ContextCompat.startActivity(holder.itemView.context, intent, Bundle.EMPTY)
        }
        holder.reportUser?.setOnClickListener {
            val intent : Intent = Intent(holder.itemView.context, AdminReportInfo::class.java)
            intent.putExtra("idReport", report[position].id)
            ContextCompat.startActivity(holder.itemView.context, intent, Bundle.EMPTY)
        }
    }

    //Выводим наши переменные
    override fun getItemCount(): Int {
        return report!!.size
    }
}