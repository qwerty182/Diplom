package com.example.diplom.interfaces

import com.example.diplom.models.*
import retrofit2.Call
import retrofit2.http.*

//Интерфейсы для вызова Api
interface InterfaceApiAuthorization {
    @Headers("Content-Type: application/json")
    @POST("AutorizationUser")
    fun userAuto(@Body userDb: ModelClassAuthorization): Call<ModelClassRegistration>
}

interface InterfaceApiRegistration {
    @Headers("Content-Type: application/json")
    @POST("RegistrationUser")
    fun userReg(@Body userReg: ModelClassRegistration): Call<ModelClassRegistration>
}

interface InterfaceApiEditUser {
    @POST("UserEdit")
    fun editUser(@Body editUs: ModelClassUserEdit): Call<ModelClassUserEdit>
}

interface InterfaceApiUserItem {
    @GET("InfoUserItem")
    fun userItem(@Query ("id") id: Int): Call<ModelClassUserInfo>
}

interface InterfaceApiSearch {
    @GET("CabinetList")
    fun CabinetList(@Query ("cabinetNumberDb") cabinetNumberDb: String): Call<List<ModelClassCabinetList>>
}

interface InterfaceApiCabinetItem {
    @GET("InfoCabinetItem")
    fun CabinetItem(@Query ("id") id: Int): Call<ModelClassCabinetItem>
}

interface InterfaceApiReportItem {
    @POST("ReportItem")
    fun addReport(@Body addRep: ModelClassReportItem): Call<ModelClassReportItem>
}

interface InterfaceApiAddCabinet {
    @POST("CabinetAdd")
    fun addCabinet(@Body addCab: ModelClassCabinetAdd): Call<ModelClassCabinetAdd>
}

interface InterfaceApiEditCabinet {
    @POST("CabinetEdit")
    fun editCabinet(@Body editCab: ModelClassCabinetEdit): Call<ModelClassCabinetEdit>
}

interface InterfaceApiReportList {
    @GET("ReportList")
    fun reportList(@Query ("id") id: Int): Call<List<ModelClassReportsList>>
}

interface InterfaceApiReportInfo {
    @GET("InfoReportItem")
    fun reportInfo(@Query ("id") id: Int): Call<ModelClassReportInfo>
}

interface InterfaceApiEditReport {
    @POST("StatusEdit")
    fun editReport(@Body editRep: ModelClassReportsEdit): Call<ModelClassReportsEdit>
}

interface InterfaceApiBookingClass {
    @POST("BookingAdd")
    fun bookingClass(@Body bookCl: ModelClassBookingClass): Call<ModelClassBookingClass>
}

interface InterfaceApiBookingList {
    @GET("BookingList")
    fun bookingHistory(@Query ("id") id: Int): Call<List<ModelClassBookingList>>
}

interface InterfaceApiBookingInfo {
    @GET("InfoBooking")
    fun bookingInfo(@Query ("id") id: Int): Call<ModelClassBookingInfo>
}

interface InterfaceApiBookingStatusEdit {
    @POST("BookingStatusEdit")
    fun bookingStatus(@Body editSt: ModelClassBookingStatusEdit): Call<ModelClassBookingStatusEdit>
}

interface InterfaceApiHistoryStudent {
    @GET("ReportListUser")
    fun reportListUser(@Query ("id") id: Int): Call<List<ModelClassStudentHistory>>
}

interface InterfaceApiHistoryAdmin {
    @GET("ReportListAdmin")
    fun reportListAdmin(): Call<List<ModelClassAdminHistory>>
}

interface InterfaceApiUserList {
    @GET("ListUser")
    fun listAdmin(): Call<List<ModelClassUserList>>
}

interface InterfaceApiUserStatusEdit {
    @POST("UserStatusEdit")
    fun userStatus(@Body editStatusUser: ModelClassUserStatusEdit): Call<ModelClassUserStatusEdit>
}