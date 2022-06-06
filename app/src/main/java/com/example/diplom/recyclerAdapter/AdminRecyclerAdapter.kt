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

class AdminRecyclerAdapter (private val cabinetAd: List<ModelClassCabinetList>?, private val activity: AdminCabinetActivity) : RecyclerView.Adapter<AdminRecyclerAdapter.MyViewHolder>()   {

    //Создаю переменные к которым буду обращаться в адаптере
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var searchImageViewAd: ImageView? = null
        var searchCabinetViewAd: TextView? = null
        var searchBuildViewAd: TextView? = null

        init {
            searchImageViewAd = itemView.findViewById(R.id.fotoImageAd)
            searchCabinetViewAd = itemView.findViewById(R.id.textViewCabinetAd)
            searchBuildViewAd = itemView.findViewById(R.id.textViewBuildAd)
        }
    }

    //идентификатор макета для отдельного элемента списка, созданный нами ранее
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_admin, parent, false)
        return MyViewHolder(itemView)
    }

    //связываем используемые текстовые метки с данными
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.searchCabinetViewAd?.text = "Кабинет ${cabinetAd!![position].number}"
        holder.searchBuildViewAd?.text =  cabinetAd!![position].build

        val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(cabinetAd[position].image)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        holder.searchImageViewAd!!.setImageBitmap(
            BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))

        holder.searchImageViewAd?.setOnClickListener {
            val intent : Intent = Intent(holder.itemView.context, AdminCabinetInfoActivity::class.java)
            intent.putExtra("idCabinet", cabinetAd[position].id)
            ContextCompat.startActivity(holder.itemView.context, intent, Bundle.EMPTY)
        }
    }

    //Выводим наши переменные
    override fun getItemCount(): Int {
        return cabinetAd!!.size
    }
}