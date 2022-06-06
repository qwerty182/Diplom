package com.example.diplom

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.example.diplom.api.RestApiManager
import com.example.diplom.models.ModelClassCabinetEdit
import kotlinx.android.synthetic.main.activity_admin_edit_cabinet.*
import java.io.ByteArrayOutputStream
import java.util.*

class AdminEditCabinetActivity : AppCompatActivity() {

    private var idCabinetEdit: Int = 0
    private var idBuildEdit: Int = 0
    private val apiService = RestApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_edit_cabinet)

        idCabinetEdit = intent.getIntExtra("idCabinetEdit", 0)

        imageCabinetEdit.setOnClickListener{
            val imageTakeIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(imageTakeIntent,1)
        }

        btnSaveCabEdit.setOnClickListener {
            editCabinet()
        }

        apiService.cabinetInfoGet(idCab = idCabinetEdit){

            editCabinetNumberEdit?.setText(it!!.cabinetNumber)
            editGeneralInfoEdit?.setText(it!!.cabinetInfo)

            if (it!!.cabinetImage != null){
                val decodedBytes = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Base64.getDecoder().decode(it.cabinetImage)
                } else {
                    TODO("VERSION.SDK_INT < O")
                }
                imageCabinetEdit!!.setImageBitmap(
                    BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size))
            }
            else{
                imageCabinetEdit.setImageResource(R.mipmap.ic_launcher)
            }
        }

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.build, R.layout.spinner_item_reg)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_reg)
        spinnerBuildEdit.setAdapter(adapter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val extras = data?.extras
            val bitmap = extras?.get("data") as Bitmap?
            imageCabinetEdit.setImageBitmap(bitmap)
        }
    }

    private fun editCabinet(){

        if (spinnerBuildEdit.selectedItem == "Нахимовский проспект"){
            idBuildEdit = 1
        }else if(spinnerBuildEdit.selectedItem == "Нежинская"){
            idBuildEdit = 2
        }

        if (editCabinetNumberEdit.text.isNullOrEmpty() || editGeneralInfoEdit.text.isNullOrEmpty()) {
            Toast.makeText(applicationContext, "Ошибка! Не все поля заполнены", Toast.LENGTH_LONG).show()
            return
        }
        val apiService = RestApiManager()
        val modelClass = ModelClassCabinetEdit(
            idCabinet = idCabinetEdit,
            cabinetNumber = editCabinetNumberEdit.text.toString(),
            cabinetImage = BitmapToByteArray().toBase64(),
            generalInformation = editGeneralInfoEdit.text.toString(),
            buildingId = idBuildEdit)

        apiService.editCabinets(modelClass) {
            if (it != null) {
                Toast.makeText(applicationContext, "Кабинет изменен", Toast.LENGTH_LONG).show()
                val intent = Intent(this@AdminEditCabinetActivity, AdminCabinetActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun BitmapToByteArray(): ByteArray
    {
        val stream = ByteArrayOutputStream()
        imageCabinetEdit.drawable.toBitmap().compress(
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