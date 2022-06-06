package com.example.diplom.api

import android.util.Log
import com.example.diplom.interfaces.*
import com.example.diplom.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiManager {
    fun userAuthorization(userDb: ModelClassAuthorization, onResult: (ModelClassRegistration?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiAuthorization::class.java)
        retrofit.userAuto(userDb).enqueue(
            object : Callback<ModelClassRegistration> {
                override fun onFailure(call: Call<ModelClassRegistration>, t: Throwable) {
                    Log.i("MyLog", t.message.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<ModelClassRegistration>, response: Response<ModelClassRegistration>) {
                    Log.i("MyLog", response.message() + response.code() + response.errorBody())
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun userRegistration(userReg: ModelClassRegistration, onResult: (ModelClassRegistration?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiRegistration::class.java)
        retrofit.userReg(userReg).enqueue(
            object : Callback<ModelClassRegistration> {
                override fun onResponse(call: Call<ModelClassRegistration>, response: Response<ModelClassRegistration>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
                override fun onFailure(call: Call<ModelClassRegistration>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun addReports(addRep: ModelClassReportItem, onResult: (ModelClassReportItem?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiReportItem::class.java)
        retrofit.addReport(addRep).enqueue(
            object : Callback<ModelClassReportItem> {
                override fun onFailure(call: Call<ModelClassReportItem>, t: Throwable) {
                    Log.i("MyLog", "drop")
                    onResult(null)
                }
                override fun onResponse(call: Call<ModelClassReportItem>, response: Response<ModelClassReportItem>) {
                    Log.i("MyLog", response.message() + response.code() + response.errorBody())
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun addCabinets(addCab: ModelClassCabinetAdd, onResult: (ModelClassCabinetAdd?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiAddCabinet::class.java)
        retrofit.addCabinet(addCab).enqueue(
            object : Callback<ModelClassCabinetAdd> {
                override fun onFailure(call: Call<ModelClassCabinetAdd>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<ModelClassCabinetAdd>, response: Response<ModelClassCabinetAdd>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun editCabinets(editCab: ModelClassCabinetEdit, onResult: (ModelClassCabinetEdit?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiEditCabinet::class.java)
        retrofit.editCabinet(editCab).enqueue(
            object : Callback<ModelClassCabinetEdit> {
                override fun onFailure(call: Call<ModelClassCabinetEdit>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<ModelClassCabinetEdit>, response: Response<ModelClassCabinetEdit>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun editUser(editUs: ModelClassUserEdit, onResult: (ModelClassUserEdit?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiEditUser::class.java)
        retrofit.editUser(editUs).enqueue(
            object : Callback<ModelClassUserEdit> {
                override fun onFailure(call: Call<ModelClassUserEdit>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<ModelClassUserEdit>, response: Response<ModelClassUserEdit>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun editReports(editRep: ModelClassReportsEdit, onResult: (ModelClassReportsEdit?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiEditReport::class.java)
        retrofit.editReport(editRep).enqueue(
            object : Callback<ModelClassReportsEdit> {
                override fun onFailure(call: Call<ModelClassReportsEdit>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<ModelClassReportsEdit>, response: Response<ModelClassReportsEdit>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun bookClass(bookCl: ModelClassBookingClass, onResult: (ModelClassBookingClass?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiBookingClass::class.java)
        retrofit.bookingClass(bookCl).enqueue(
            object : Callback<ModelClassBookingClass> {
                override fun onFailure(call: Call<ModelClassBookingClass>, t: Throwable) {
                    Log.i("MyLog", t.message.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<ModelClassBookingClass>, response: Response<ModelClassBookingClass>) {
                    Log.i("MyLog", response.message() + response.code() + response.errorBody())
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun editBooking(editBook: ModelClassBookingStatusEdit, onResult: (ModelClassBookingStatusEdit?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiBookingStatusEdit::class.java)
        retrofit.bookingStatus(editBook).enqueue(
            object : Callback<ModelClassBookingStatusEdit> {
                override fun onFailure(call: Call<ModelClassBookingStatusEdit>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<ModelClassBookingStatusEdit>, response: Response<ModelClassBookingStatusEdit>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

    fun searchListGet(searchResult: String, onResult: (List<ModelClassCabinetList>?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiSearch::class.java)
        retrofit.CabinetList(searchResult).enqueue(
            object : Callback<List<ModelClassCabinetList>> {
                override fun onResponse(call: Call<List<ModelClassCabinetList>>, response: Response<List<ModelClassCabinetList>>) {
                    val search = response.body()
                    onResult(search)
                }
                override fun onFailure(call: Call<List<ModelClassCabinetList>>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun cabinetInfoGet(idCab: Int, onResult: (ModelClassCabinetItem?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiCabinetItem::class.java)
        retrofit.CabinetItem(idCab).enqueue(
            object : Callback<ModelClassCabinetItem> {
                override fun onResponse(call: Call<ModelClassCabinetItem>, response: Response<ModelClassCabinetItem>) {
                    val search = response.body()
                    onResult(search)
                }
                override fun onFailure(call: Call<ModelClassCabinetItem>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun reportInfoGet(idRep: Int, onResult: (ModelClassReportInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiReportInfo::class.java)
        retrofit.reportInfo(idRep).enqueue(
            object : Callback<ModelClassReportInfo> {
                override fun onResponse(call: Call<ModelClassReportInfo>, response: Response<ModelClassReportInfo>) {
                    val search = response.body()
                    onResult(search)
                }
                override fun onFailure(call: Call<ModelClassReportInfo>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun reportListGet(idCabinet: Int, onResult: (List<ModelClassReportsList>?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiReportList::class.java)
        retrofit.reportList(idCabinet).enqueue(
            object : Callback<List<ModelClassReportsList>> {
                override fun onResponse(call: Call<List<ModelClassReportsList>>, response: Response<List<ModelClassReportsList>>) {
                    val search = response.body()
                    onResult(search)
                }
                override fun onFailure(call: Call<List<ModelClassReportsList>>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun bookingInfoGet(idBook: Int, onResult: (ModelClassBookingInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiBookingInfo::class.java)
        retrofit.bookingInfo(idBook).enqueue(
            object : Callback<ModelClassBookingInfo> {
                override fun onResponse(call: Call<ModelClassBookingInfo>, response: Response<ModelClassBookingInfo>) {
                    val search = response.body()
                    onResult(search)
                }
                override fun onFailure(call: Call<ModelClassBookingInfo>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun bookingListGet(idBooking: Int, onResult: (List<ModelClassBookingList>?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiBookingList::class.java)
        retrofit.bookingHistory(idBooking).enqueue(
            object : Callback<List<ModelClassBookingList>> {
                override fun onResponse(call: Call<List<ModelClassBookingList>>, response: Response<List<ModelClassBookingList>>) {
                    val search = response.body()
                    onResult(search)
                }
                override fun onFailure(call: Call<List<ModelClassBookingList>>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun userInfoGet(idUs: Int, onResult: (ModelClassUserInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiUserItem::class.java)
        retrofit.userItem(idUs).enqueue(
            object : Callback<ModelClassUserInfo> {
                override fun onResponse(call: Call<ModelClassUserInfo>, response: Response<ModelClassUserInfo>) {
                    val search = response.body()
                    onResult(search)
                }
                override fun onFailure(call: Call<ModelClassUserInfo>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun reportStudentHistoryGet(idHistory: Int, onResult: (List<ModelClassStudentHistory>?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiHistoryStudent::class.java)
        retrofit.reportListUser(idHistory).enqueue(
            object : Callback<List<ModelClassStudentHistory>> {
                override fun onResponse(call: Call<List<ModelClassStudentHistory>>, response: Response<List<ModelClassStudentHistory>>) {
                    val search = response.body()
                    onResult(search)
                }
                override fun onFailure(call: Call<List<ModelClassStudentHistory>>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun reportAdminHistoryGet(onResult: (List<ModelClassAdminHistory>?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiHistoryAdmin::class.java)
        retrofit.reportListAdmin().enqueue(
            object : Callback<List<ModelClassAdminHistory>> {
                override fun onResponse(call: Call<List<ModelClassAdminHistory>>, response: Response<List<ModelClassAdminHistory>>) {
                    val search = response.body()
                    onResult(search)
                }
                override fun onFailure(call: Call<List<ModelClassAdminHistory>>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun userListAdminGet(onResult: (List<ModelClassUserList>?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiUserList::class.java)
        retrofit.listAdmin().enqueue(
            object : Callback<List<ModelClassUserList>> {
                override fun onResponse(call: Call<List<ModelClassUserList>>, response: Response<List<ModelClassUserList>>) {
                    val search = response.body()
                    onResult(search)
                }
                override fun onFailure(call: Call<List<ModelClassUserList>>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }

    fun editUserStatus(editUs: ModelClassUserStatusEdit, onResult: (ModelClassUserStatusEdit?) -> Unit){
        val retrofit = ServiceBuilder.buildService(InterfaceApiUserStatusEdit::class.java)
        retrofit.userStatus(editUs).enqueue(
            object : Callback<ModelClassUserStatusEdit> {
                override fun onFailure(call: Call<ModelClassUserStatusEdit>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<ModelClassUserStatusEdit>, response: Response<ModelClassUserStatusEdit>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}