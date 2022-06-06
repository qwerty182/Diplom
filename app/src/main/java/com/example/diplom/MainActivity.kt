package com.example.diplom

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.diplom.api.RestApiManager
import com.example.diplom.models.ModelClassAuthorization
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permission: String = Manifest.permission.CAMERA
        val grant = ContextCompat.checkSelfPermission(this, permission)
        if (grant != PackageManager.PERMISSION_GRANTED) {
            val permission_list = arrayOfNulls<String>(1)
            permission_list[0] = permission
            ActivityCompat.requestPermissions(this, permission_list, 1)
        }

        val sharedPreferencesSave = getSharedPreferences("Save", Context.MODE_PRIVATE)

        btnAuto.setOnClickListener {
            if (checkSave.isChecked){
                val saveLogin = editLogin.text.toString().trim()
                val savePassword = editPassword.text.toString().trim()

                val editor = sharedPreferencesSave.edit()

                editor.putString("SaveLogin", saveLogin)
                editor.putString("SavePassword", savePassword)

                editor.apply()
            }

            authorization()
        }

        btnReg.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }

        editLogin.setText(sharedPreferencesSave.getString("SaveLogin", "").toString())
        editPassword.setText(sharedPreferencesSave.getString("SavePassword", "").toString())
    }

    private fun authorization() {
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val apiService = RestApiManager()
        val userLog = ModelClassAuthorization(
            login = editLogin.text.toString(),
            password = editPassword.text.toString())

        apiService.userAuthorization(userLog) {
            Log.i("MyLog", it?.idUser.toString())
            if (it?.idUser != null && it.roleId == 1) {
                Toast.makeText(applicationContext, "Вход...", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity, StudentCabinetActivity::class.java)

                val login = editLogin.text.toString().trim()
                val idUser = Integer.parseInt(it.idUser.toString().trim())
                val password = editPassword.text.toString().trim()

                val editor = sharedPreferences.edit()

                editor.putString("LOGIN", login)
                editor.putInt("IDUSER", idUser)
                editor.putString("PASSWORD", password)

                editor.apply()

                startActivity(intent)
            } else if (it?.idUser != null && it.roleId == 2) {
                Toast.makeText(applicationContext, "Вход...", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity, AdminCabinetActivity::class.java)

                val loginAd = editLogin.text.toString().trim()
                val idUserAd = Integer.parseInt(it.idUser.toString().trim())
                val passwordAd = editPassword.text.toString().trim()

                val editor = sharedPreferences.edit()

                editor.putString("LOGINADMIN", loginAd)
                editor.putInt("IDUSERADMIN", idUserAd)
                editor.putString("PASSWORDADMIN", passwordAd)

                editor.apply()

                startActivity(intent)
            } else if (it?.idUser != null && it.roleId == 3){
                Toast.makeText(applicationContext, "Вход...", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity, TeacherCabinetActivity::class.java)

                val loginTc = editLogin.text.toString().trim()
                val idUserTc = Integer.parseInt(it.idUser.toString().trim())
                val passwordTc = editPassword.text.toString().trim()

                val editor = sharedPreferences.edit()

                editor.putString("LOGINTEACHER", loginTc)
                editor.putInt("IDUSERTEACHER", idUserTc)
                editor.putString("PASSWORDTEACHER", passwordTc)

                editor.apply()

                startActivity(intent)
            }
            else {
                Toast.makeText(applicationContext, "Такого пользователя не существует. Зарегистрироваться?", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "permission not granted", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}