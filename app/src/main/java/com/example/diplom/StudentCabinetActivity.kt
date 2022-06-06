package com.example.diplom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.api.RestApiManager
import com.example.diplom.recyclerAdapter.CustomRecyclerAdapter
import kotlinx.android.synthetic.main.activity_student_cabinet.*


class StudentCabinetActivity : AppCompatActivity() {

    private val apiService = RestApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_cabinet)
    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("NAME", "")
        if (name != "") {
            apiService.searchListGet(searchResult = name.toString()) {
                val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                recyclerView.layoutManager = linearLayoutManager
                recyclerView.adapter = CustomRecyclerAdapter(it, this@StudentCabinetActivity)
            }
        }
        else {
            apiService.searchListGet(searchResult = name.toString()) {
                val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                recyclerView.layoutManager = linearLayoutManager
                recyclerView.adapter = CustomRecyclerAdapter(it, this@StudentCabinetActivity)
            }
        }

        btnUserStudent.setOnClickListener {
            val intent = Intent(this@StudentCabinetActivity, StudentInfoActivity::class.java)
            startActivity(intent)
        }

        fun performSearch(){
            if (!editSearch!!.text.isNullOrEmpty()){
                apiService.searchListGet(searchResult = editSearch.text.toString()) {
                    val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
                    linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                    recyclerView.layoutManager = linearLayoutManager
                    recyclerView.adapter = CustomRecyclerAdapter(it, this@StudentCabinetActivity)
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

        editSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })
    }
}