package com.example.diplom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diplom.api.RestApiManager
import com.example.diplom.recyclerAdapter.AdminUserListRecyclerAdapter
import kotlinx.android.synthetic.main.activity_admin_user_list.*

class AdminUserListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_user_list)

        val apiService = RestApiManager()
        apiService.userListAdminGet {
            val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerViewListUser.layoutManager = linearLayoutManager
            recyclerViewListUser.adapter = AdminUserListRecyclerAdapter(it, this@AdminUserListActivity)
        }
    }
}