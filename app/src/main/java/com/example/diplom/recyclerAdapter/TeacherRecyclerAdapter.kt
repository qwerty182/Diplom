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
import com.example.diplom.*
import com.example.diplom.models.ModelClassCabinetList
import java.util.*

class TeacherRecyclerAdapter (private val teacher: List<ModelClassCabinetList>?, private val activity: TeacherCabinetActivity) : RecyclerView.Adapter<TeacherRecyclerAdapter.MyViewHolder>() {
    //Создаю переменные к которым буду обращаться в адаптере
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var searchImageView: ImageView? = null
        var searchCabinetView: TextView? = null
        var searchBuildView: TextView? = null

        init {
            searchImageView = itemView.findViewById(R.id.fotoImageTc)
            searchCabinetView = itemView.findViewById(R.id.textViewCabinetTc)
            searchBuildView = itemView.findViewById(R.id.textViewBuildTc)
        }
    }

    //идентификатор макета для отдельного элемента списка, созданный нами ранее
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_teacher, parent, false)
        return MyViewHolder(itemView)
    }

    //связываем используемые текстовые метки с данными
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.searchCabinetView?.text = "Кабинет ${teacher!![position].number}"
        holder.searchBuildView?.text = teacher[position].build

        val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(teacher[position].image)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        holder.searchImageView!!.setImageBitmap(
            BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))

        holder.searchImageView?.setOnClickListener {
            val intent : Intent = Intent(holder.itemView.context, TeacherCabinetInfoActivity::class.java)
            intent.putExtra("idCabinetTc", teacher[position].id)
            ContextCompat.startActivity(holder.itemView.context, intent, Bundle.EMPTY)
        }
    }

    //Выводим наши переменные
    override fun getItemCount(): Int {
        return teacher!!.size
    }
}