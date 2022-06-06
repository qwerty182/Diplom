package com.example.diplom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.diplom.api.RestApiManager
import com.example.diplom.models.ModelClassRegistration
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_student_report_add.*

class RegistrationActivity : AppCompatActivity() {

    private var idRole: Int = 0
    private var status: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        btnReg.setOnClickListener {
            registration()
        }

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.role, R.layout.spinner_item_reg)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_reg)
        spinnerRole.setAdapter(adapter)
    }

    private fun registration(){
        if (editLogin.text.isNullOrEmpty() || editPassword.text.isNullOrEmpty()
            || editLastName.text.isNullOrEmpty() || editFirstName.text.isNullOrEmpty()
            || editMiddleName.text.isNullOrEmpty()) {
            Toast.makeText(applicationContext, "Ошибка! Не все поля заполнены", Toast.LENGTH_LONG).show()
            return
        }
        if (editPassword.text.toString() != editPovPassword.text.toString()){
            Toast.makeText(applicationContext, "Ошибка! Пароли не совпадают", Toast.LENGTH_LONG).show()
            return
        }

        if (spinnerRole.selectedItem == "Студент"){
            idRole = 1
            status = true
        }
        else if (spinnerRole.selectedItem == "Преподаватель") {
            idRole = 3
            status = false
        }

        val apiService = RestApiManager()
        val modelClass = ModelClassRegistration(
            login = editLogin.text.toString(),
            password = editPassword.text.toString(),
            lastName = editLastName.text.toString(),
            firstName = editFirstName.text.toString(),
            middleName = editMiddleName.text.toString(),
            roleId = idRole,
            statusUser = status)

        apiService.userRegistration(modelClass) {
            if (it != null && status) {
                Toast.makeText(applicationContext, "Пользователь добавлен", Toast.LENGTH_LONG).show()
                val intent = Intent(this@RegistrationActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            else if (it != null && !status){
                Toast.makeText(applicationContext, "Ваша учетная запись отпавлена рассмотрение", Toast.LENGTH_LONG).show()
                val intent = Intent(this@RegistrationActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
}