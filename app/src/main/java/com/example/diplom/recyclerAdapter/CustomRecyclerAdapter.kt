package com.example.diplom.recyclerAdapter

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.*
import com.example.diplom.models.ModelClassCabinetList
import java.util.*


class CustomRecyclerAdapter (private val cabinet: List<ModelClassCabinetList>?, private val activity: StudentCabinetActivity) : RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>()  {


    //Создаю переменные к которым буду обращаться в адаптере
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var searchImageView: ImageView? = null
        var searchCabinetView: TextView? = null
        var searchBuildView: TextView? = null

        init {
            searchImageView = itemView.findViewById(R.id.fotoImage)
            searchCabinetView = itemView.findViewById(R.id.textViewCabinet)
            searchBuildView = itemView.findViewById(R.id.textViewBuild)
        }
    }

    //идентификатор макета для отдельного элемента списка, созданный нами ранее
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    //связываем используемые текстовые метки с данными
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.searchCabinetView?.text = "Кабинет ${cabinet!![position].number}"
        holder.searchBuildView?.text = cabinet!![position].build

        val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(cabinet[position].image)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        holder.searchImageView!!.setImageBitmap(
            BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))

        holder.searchImageView?.setOnClickListener {
            val intent : Intent = Intent(holder.itemView.context, StudentDetailCabinetActivity::class.java)
            intent.putExtra("idCabinet", cabinet[position].id)
            startActivity(holder.itemView.context, intent, Bundle.EMPTY)
        }
    }

    //Выводим наши переменные
    override fun getItemCount(): Int {
        return cabinet!!.size
    }

}