package com.example.diplom

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.api.RestApiManager
import com.example.diplom.recyclerAdapter.AdminRecyclerAdapter
import kotlinx.android.synthetic.main.activity_admin_cabinet.*

class AdminCabinetActivity : AppCompatActivity() {

    private val apiService = RestApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_cabinet)

        btnAddCabinet.setOnClickListener {
            val intent = Intent(this@AdminCabinetActivity, AdminAddCabinetActivity::class.java)
            startActivity(intent)
        }

        btnUserAdmin.setOnClickListener {
            val intent = Intent(this@AdminCabinetActivity, AdminInfoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("NAME", "")
        if (name != ""){
            apiService.searchListGet(searchResult = name.toString()) {
                val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                recyclerView.layoutManager = linearLayoutManager
                recyclerView.adapter = AdminRecyclerAdapter(it, this@AdminCabinetActivity)
            }
        }
        else{
            apiService.searchListGet(searchResult = name.toString()) {
                val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                recyclerView.layoutManager = linearLayoutManager
                recyclerView.adapter = AdminRecyclerAdapter(it, this@AdminCabinetActivity)
            }
        }

        fun performSearch(){
            if (!editSearch!!.text.isNullOrEmpty()){
                apiService.searchListGet(searchResult = editSearch.text.toString()) {
                    val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
                    linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                    recyclerView.layoutManager = linearLayoutManager
                    recyclerView.adapter = AdminRecyclerAdapter(it, this@AdminCabinetActivity)
                    val name = editSearch.text.toString().trim()
                    val editor = sharedPreferences.edit()
                    editor.putString("NAME", name)
                    editor.apply()
                    Toast.makeText(applicationContext, "Поиск...", Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(applicationContext, "Ошибка", Toast.LENGTH_LONG).show()
            }
        }

        editSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })
    }
}