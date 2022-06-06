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
import kotlinx.android.synthetic.main.activity_student_info.*
import java.io.ByteArrayOutputStream
import java.util.*

class StudentInfoActivity : AppCompatActivity() {

    private val apiService = RestApiManager()
    private var idUser: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_info)

        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        idUser = sharedPreferences.getInt("IDUSER", 0)

        Log.e("MyLog", idUser.toString())

        btnSaveUser.setOnClickListener {
            editUser()
        }

        btnExit.setOnClickListener {
            val intent = Intent(this@StudentInfoActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        btnHistory.setOnClickListener {
            val intent = Intent(this@StudentInfoActivity, StudentReportHistoryActivity::class.java)
            startActivity(intent)
        }

        apiService.userInfoGet(idUs = idUser){
            editLogin?.setText(it!!.login.toString())

            if (it!!.imageUser != null){
                val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Base64.getDecoder().decode(it.imageUser)
                } else {
                    TODO("VERSION.SDK_INT < O")
                }
                imageUserEdit!!.setImageBitmap(
                    BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))
            }
            else{
                imageUserEdit.setImageResource(R.drawable.ic_baseline_insert_photo_24)
            }

            editLastName?.setText(it.lastName.toString())
            editFirstName?.setText(it.firstName.toString())
            editMiddleName?.setText(it.middleName.toString())
        }

        imageUserEdit.setOnClickListener{
            val imageTakeIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(imageTakeIntent,1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val extras = data?.extras
            val bitmap = extras?.get("data") as Bitmap?
            imageUserEdit.setImageBitmap(bitmap)
        }
    }

    private fun editUser(){
        if (editLogin.text.isNullOrEmpty() || editLastName.text.isNullOrEmpty() || editFirstName.text.isNullOrEmpty() || editMiddleName.text.isNullOrEmpty()) {
            Toast.makeText(applicationContext, "Ошибка! Не все поля заполнены", Toast.LENGTH_LONG).show()
            return
        }

        val modelClass = ModelClassUserEdit(
            idUser = idUser,
            login = editLogin.text.toString(),
            lastName = editLastName.text.toString(),
            firstName = editFirstName.text.toString(),
            middleName = editMiddleName.text.toString(),
            imageUser = BitmapToByteArray().toBase64()
        )

        apiService.editUser(modelClass) {
            if (it != null) {
                Toast.makeText(applicationContext, "Пользователь изменен", Toast.LENGTH_LONG).show()
                val intent = Intent(this@StudentInfoActivity, StudentCabinetActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }

    private fun BitmapToByteArray(): ByteArray
    {
        val stream = ByteArrayOutputStream()
        imageUserEdit.drawable.toBitmap().compress(
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