package com.example.diplom.models

import com.google.gson.annotations.SerializedName

data class ModelClassAuthorization(
    @SerializedName("login"    ) var login    : String? = null,
    @SerializedName("password" ) var password : String? = null
)

data class ModelClassRegistration(
    @SerializedName("idUser"           ) var idUser           : Int?              = null,
    @SerializedName("login"            ) var login            : String?           = null,
    @SerializedName("password"         ) var password         : String?           = null,
    @SerializedName("lastName"         ) var lastName         : String?           = null,
    @SerializedName("firstName"        ) var firstName        : String?           = null,
    @SerializedName("middleName"       ) var middleName       : String?           = null,
    @SerializedName("roleId"           ) var roleId           : Int?              = null,
    @SerializedName("imageUser"        ) var imageUser        : String?           = null,
    @SerializedName("statusUser"       ) var statusUser       : Boolean?          = null,
    @SerializedName("role"             ) var role             : String?           = null,
    @SerializedName("bookingAnOffices" ) var bookingAnOffices : ArrayList<String> = arrayListOf(),
    @SerializedName("reports"          ) var reports          : ArrayList<String> = arrayListOf()
)

data class ModelClassUserInfo(
    @SerializedName("idUser"     ) var idUser     : Int?    = null,
    @SerializedName("login"      ) var login      : String? = null,
    @SerializedName("password"   ) var password   : String? = null,
    @SerializedName("lastName"   ) var lastName   : String? = null,
    @SerializedName("firstName"  ) var firstName  : String? = null,
    @SerializedName("middleName" ) var middleName : String? = null,
    @SerializedName("roleId"     ) var roleId     : Int?    = null,
    @SerializedName("imageUser"  ) var imageUser  : String?
)

data class ModelClassCabinetList(
    @SerializedName("id") var id : Int,
    @SerializedName("image") var image : String,
    @SerializedName("number") var number : String,
    @SerializedName("build") var build : String
)

data class ModelClassCabinetItem(
    @SerializedName("idCabineta") var idCabineta : Int,
    @SerializedName("cabinetNumber") var cabinetNumber : String,
    @SerializedName("cabinetImage") var cabinetImage : String,
    @SerializedName("cabinetInfo") var cabinetInfo : String,
    @SerializedName("cabinetBuidlId") var cabinetBuidlId : Int,
    @SerializedName("cabinetBuild") var cabinetBuild : String,
)

data class ModelClassReportItem(
    @SerializedName("comment") var comment : String,
    @SerializedName("images") var images : String,
    @SerializedName("dateOfLocations") var dateOfLocations : String,
    @SerializedName("status") var status : Boolean,
    @SerializedName("userId") var userId : Int,
    @SerializedName("cabinetId") var cabinetId : Int,
    @SerializedName("reportTypeId") var reportTypeId : Int
)

data class ModelClassCabinetAdd(
    @SerializedName("cabinetNumber") var cabinetNumber : String,
    @SerializedName("cabinetImage") var cabinetImage : String,
    @SerializedName("generalInformation") var generalInformation : String,
    @SerializedName("buildingId") var buildingId : Int
)

data class ModelClassCabinetEdit(
    @SerializedName("idCabinet") var idCabinet : Int,
    @SerializedName("cabinetNumber") var cabinetNumber : String,
    @SerializedName("cabinetImage") var cabinetImage : String,
    @SerializedName("generalInformation") var generalInformation : String,
    @SerializedName("buildingId") var buildingId : Int
)

data class ModelClassReportsList(
    @SerializedName("id") var id : Int,
    @SerializedName("dateReport") var dateReport : String,
    @SerializedName("comment") var comment : String,
    @SerializedName("imageReport") var imageReport : String,
    @SerializedName("status") var status : Boolean,
    @SerializedName("userLast") var userLast : String,
    @SerializedName("userName") var userName : String,
    @SerializedName("userMiddle") var userMiddle : String,
    @SerializedName("reportType") var reportType : String
)

data class ModelClassReportInfo(
    @SerializedName("idReport") var idReport : Int,
    @SerializedName("commentReport") var commentReport : String,
    @SerializedName("imageReport") var imageReport : String,
    @SerializedName("dateReport") var dateReport : String,
    @SerializedName("statusReport") var statusReport : Boolean,
    @SerializedName("reportTypeId") var reportTypeId : Int,
    @SerializedName("nameType") var nameType : String,
    @SerializedName("cabinetId") var cabinetId : Int,
    @SerializedName("cabinetName") var cabinetName : String,
    @SerializedName("build") var build : String,
    @SerializedName("userId") var userId : Int,
    @SerializedName("userLogin") var userLogin : String,
    @SerializedName("userLast") var userLast : String,
    @SerializedName("userName") var userName : String,
    @SerializedName("userMiddle") var userMiddle : String,
)

data class ModelClassReportsEdit(
    @SerializedName("idReport") var idReport : Int,
    @SerializedName("status") var status : Boolean
)

data class ModelClassBookingClass(
    @SerializedName("idBookingAnOffice" ) var idBookingAnOffice : Int?     = null,
    @SerializedName("bookingDate"       ) var bookingDate       : String?  = null,
    @SerializedName("bookingStatus"     ) var bookingStatus     : Boolean? = null,
    @SerializedName("userId"            ) var userId            : Int?     = null,
    @SerializedName("bookingTimeId"     ) var bookingTimeId     : Int?     = null,
    @SerializedName("cabinetId"         ) var cabinetId         : Int?     = null,
    @SerializedName("bookingTime"       ) var bookingTime       : String?  = null,
    @SerializedName("cabinet"           ) var cabinet           : String?  = null,
    @SerializedName("user"              ) var user              : String?  = null
)

data class ModelClassBookingList(
    @SerializedName("id"            ) var id            : Int?     = null,
    @SerializedName("bookingDate"   ) var bookingDate   : String?  = null,
    @SerializedName("bookingStatus" ) var bookingStatus : Boolean? = null,
    @SerializedName("userId"        ) var userId        : Int?     = null,
    @SerializedName("bookingTime"   ) var bookingTime   : String?  = null,
    @SerializedName("bookingTimeId" ) var bookingTimeId : Int?     = null,
    @SerializedName("cabinetId"     ) var cabinetId     : Int?     = null,
    @SerializedName("cabinetName"   ) var cabinetName   : String?  = null
)

data class ModelClassBookingInfo(
    @SerializedName("idBooking"     ) var idBooking     : Int?     = null,
    @SerializedName("cabinet"       ) var cabinet       : String?  = null,
    @SerializedName("time"          ) var time          : String?  = null,
    @SerializedName("date"          ) var date          : String?  = null,
    @SerializedName("statusBooking" ) var statusBooking : Boolean? = null,
    @SerializedName("userId"        ) var userId        : Int?     = null
)

data class ModelClassBookingStatusEdit(
    @SerializedName("idBookingAnOffice" ) var idBookingAnOffice : Int?     = null,
    @SerializedName("bookingStatus"     ) var bookingStatus     : Boolean? = null
)

data class ModelClassUserEdit(
    @SerializedName("idUser"     ) var idUser     : Int?    = null,
    @SerializedName("login"      ) var login      : String? = null,
    @SerializedName("imageUser"  ) var imageUser  : String? = null,
    @SerializedName("lastName"   ) var lastName   : String? = null,
    @SerializedName("firstName"  ) var firstName  : String? = null,
    @SerializedName("middleName" ) var middleName : String? = null
)

data class ModelClassStudentHistory(
    @SerializedName("id"          ) var id          : Int?     = null,
    @SerializedName("dateReport"  ) var dateReport  : String?  = null,
    @SerializedName("comment"     ) var comment     : String?  = null,
    @SerializedName("imageReport" ) var imageReport : String?  = null,
    @SerializedName("status"      ) var status      : Boolean? = null,
    @SerializedName("reportType"  ) var reportType  : String?  = null,
    @SerializedName("cabinet"     ) var cabinet     : String?  = null
)

data class ModelClassAdminHistory(
    @SerializedName("id"          ) var id          : Int?     = null,
    @SerializedName("dateReport"  ) var dateReport  : String?  = null,
    @SerializedName("comment"     ) var comment     : String?  = null,
    @SerializedName("imageReport" ) var imageReport : String?  = null,
    @SerializedName("status"      ) var status      : Boolean? = null,
    @SerializedName("reportType"  ) var reportType  : String?  = null,
    @SerializedName("cabinet"     ) var cabinet     : String?  = null
)

data class ModelClassUserList(
    @SerializedName("id"         ) var id         : Int?     = null,
    @SerializedName("login"      ) var login      : String?  = null,
    @SerializedName("lastName"   ) var lastName   : String?  = null,
    @SerializedName("firstName"  ) var firstName  : String?  = null,
    @SerializedName("middleName" ) var middleName : String?  = null,
    @SerializedName("role"       ) var role       : String?  = null,
    @SerializedName("statusUser" ) var statusUser : Boolean? = null
)

data class ModelClassUserStatusEdit(
    @SerializedName("idUser"    ) var idUser     : Int?     = null,
    @SerializedName("statusUser") var statusUser : Boolean? = null
)