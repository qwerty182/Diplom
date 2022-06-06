package com.example.diplom

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.example.diplom.api.RestApiManager
import com.example.diplom.models.ModelClassUserEdit
import kotlinx.android.synthetic.main.activity_teacher_info.*
import java.io.ByteArrayOutputStream
import java.util.*

class TeacherInfoActivity : AppCompatActivity() {

    private val apiService = RestApiManager()
    private var idUser: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_info)

        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        idUser = sharedPreferences.getInt("IDUSERTEACHER", 0)

        Log.e("MyLog", idUser.toString())

        btnSaveUserTc.setOnClickListener {
            editUser()
        }

        btnHistoryTc.setOnClickListener {
            val intent = Intent(this@TeacherInfoActivity, TeacherBookingHistoryActivity::class.java)
            startActivity(intent)
        }

        btnExitTc.setOnClickListener {
            val intent = Intent(this@TeacherInfoActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        apiService.userInfoGet(idUs = idUser){
            editLoginTc?.setText(it!!.login.toString())

            if (it!!.imageUser != null){
                val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Base64.getDecoder().decode(it.imageUser)
                } else {
                    TODO("VERSION.SDK_INT < O")
                }
                imageUserEditTc!!.setImageBitmap(
                    BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))
            }
            else{
                imageUserEditTc.setImageResource(R.drawable.ic_baseline_insert_photo_24)
            }

            editLastNameTc?.setText(it.lastName.toString())
            editFirstNameTc?.setText(it.firstName.toString())
            editMiddleNameTc?.setText(it.middleName.toString())
        }

        imageUserEditTc.setOnClickListener{
            val imageTakeIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(imageTakeIntent,1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val extras = data?.extras
            val bitmap = extras?.get("data") as Bitmap?
            imageUserEditTc.setImageBitmap(bitmap)
        }
    }

    private fun editUser(){
        if (editLoginTc.text.isNullOrEmpty() || editLastNameTc.text.isNullOrEmpty() || editFirstNameTc.text.isNullOrEmpty() || editMiddleNameTc.text.isNullOrEmpty()) {
            Toast.makeText(applicationContext, "Ошибка! Не все поля заполнены", Toast.LENGTH_LONG).show()
            return
        }

        val modelClass = ModelClassUserEdit(
            idUser = idUser,
            login = editLoginTc.text.toString(),
            lastName = editLastNameTc.text.toString(),
            firstName = editFirstNameTc.text.toString(),
            middleName = editMiddleNameTc.text.toString(),
            imageUser = BitmapToByteArray().toBase64()
        )

        apiService.editUser(modelClass) {
            if (it != null) {
                Toast.makeText(applicationContext, "Пользователь изменен", Toast.LENGTH_LONG).show()
                val intent = Intent(this@TeacherInfoActivity, TeacherCabinetActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }

    private fun BitmapToByteArray(): ByteArray
    {
        val stream = ByteArrayOutputStream()
        imageUserEditTc.drawable.toBitmap().compress(
            Bitmap.CompressFormat.JPEG,
            100, stream)
        val bitmapdata: ByteArray = stream.toByteArray()
        return bitmapdata
    }
    private fun ByteArray.toBase64(): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String(Base64.getEncoder().encode(this))
        } else {
            TODO("VERSION.SDK_INT < O")
        }
}