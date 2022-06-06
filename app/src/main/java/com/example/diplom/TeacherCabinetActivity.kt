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
import com.example.diplom.recyclerAdapter.TeacherRecyclerAdapter
import kotlinx.android.synthetic.main.activity_teacher_cabinet.*

class TeacherCabinetActivity : AppCompatActivity() {

    private val apiService = RestApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_cabinet)

        btnUserTeacher.setOnClickListener {
            val intent = Intent(this@TeacherCabinetActivity, TeacherInfoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("NAME", "")
        if (name != ""){
            apiService.searchListGet(searchResult = name.toString()){
                val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                recyclerViewTeacher.layoutManager = linearLayoutManager
                recyclerViewTeacher.adapter = TeacherRecyclerAdapter(it, this@TeacherCabinetActivity)
            }
        }
        else {
            apiService.searchListGet(searchResult = name.toString()){
                val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                recyclerViewTeacher.layoutManager = linearLayoutManager
                recyclerViewTeacher.adapter = TeacherRecyclerAdapter(it, this@TeacherCabinetActivity)
            }
        }

        fun performSearch(){
            if (!editSearchTeacher!!.text.isNullOrEmpty()){
                apiService.searchListGet(searchResult = editSearchTeacher.text.toString()){
                    val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
                    linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                    recyclerViewTeacher.layoutManager = linearLayoutManager
                    recyclerViewTeacher.adapter = TeacherRecyclerAdapter(it, this@TeacherCabinetActivity)
                    val name = editSearchTeacher.text.toString().trim()
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

        editSearchTeacher.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })
    }
}