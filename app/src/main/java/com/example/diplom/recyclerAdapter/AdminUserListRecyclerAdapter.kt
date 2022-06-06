package com.example.diplom.recyclerAdapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.AdminInfoActivity
import com.example.diplom.AdminUserListActivity
import com.example.diplom.R
import com.example.diplom.api.RestApiManager
import com.example.diplom.models.ModelClassUserList
import com.example.diplom.models.ModelClassUserStatusEdit

class AdminUserListRecyclerAdapter (private val user: List<ModelClassUserList>?, private val activity: AdminUserListActivity) : RecyclerView.Adapter<AdminUserListRecyclerAdapter.MyViewHolder>() {
    //Создаю переменные к которым буду обращаться в адаптере
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var userId: TextView? = null
        var userLogin: TextView? = null
        var userFIO: TextView? = null
        var userRole: TextView? = null
        var userStatus: TextView? = null
        var userBtn: Button? = null
        var userBtnCan: Button? = null

        init {
            userId = itemView.findViewById(R.id.textViewUserId)
            userLogin = itemView.findViewById(R.id.textViewUserLogin)
            userFIO = itemView.findViewById(R.id.textViewUserFIO)
            userRole = itemView.findViewById(R.id.textViewUserRole)
            userStatus = itemView.findViewById(R.id.textViewUserStatus)
            userBtn = itemView.findViewById(R.id.btnConfirm)
            userBtnCan = itemView.findViewById(R.id.btnCancel)
        }
    }

    //идентификатор макета для отдельного элемента списка, созданный нами ранее
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_user, parent, false)
        return MyViewHolder(itemView)
    }

    //связываем используемые текстовые метки с данными
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.userId?.text = "№${user!![position].id}"
        holder.userLogin?.text = user[position].login
        holder.userFIO?.text = "${user[position].lastName} ${user[position].firstName} ${user[position].middleName}"
        holder.userRole?.text = user[position].role
        holder.userStatus?.text = "Ожидает подтверждения"

        holder.userBtn?.setOnClickListener {
            val apiService = RestApiManager()
            val modelClass = ModelClassUserStatusEdit(
                idUser = user[position].id,
                statusUser = true)

            apiService.editUserStatus(modelClass) {
                if (it != null) {
                    Toast.makeText(holder.itemView.context, "Пользователь добавлен", Toast.LENGTH_LONG).show()
                    val intent = Intent(holder.itemView.context, AdminInfoActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    ContextCompat.startActivity(holder.itemView.context, intent, Bundle.EMPTY)
                }
            }
        }

        holder.userBtnCan?.setOnClickListener {
            val apiService = RestApiManager()
            val modelClass = ModelClassUserStatusEdit(
                idUser = user[position].id,
                statusUser = true)

            apiService.editUserStatus(modelClass) {
                if (it != null) {
                    Toast.makeText(holder.itemView.context, "Запрос отклонен", Toast.LENGTH_LONG).show()
                    val intent = Intent(holder.itemView.context, AdminInfoActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    ContextCompat.startActivity(holder.itemView.context, intent, Bundle.EMPTY)
                }
            }
        }
    }

    //Выводим наши переменные
    override fun getItemCount(): Int {
        return user!!.size
    }
}