package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class DataModel(
    val result: Resultdata,
    val records: Recordsdata,
    val message: String,
    val status: String
)

data class Resultdata(
    @SerializedName("resource_id")
    var resource_id: String,

    @SerializedName("fields")
    var fields: List<fields>,
)

data class Recordsdata(
    @SerializedName("datasetDescription")
    var datasetDescription: String,

    @SerializedName("location")
    var location: List<location>,
)


data class fields(
    @SerializedName("id")
    var id: String,

    @SerializedName("type")
    var type: String,
)

data class location(
    @SerializedName("locationName")
    var locationName: String,

    @SerializedName("weatherElement")
    var weatherElement: List<weatherElement>,
)

data class weatherElement(
    @SerializedName("elementName")
    var elementName: String,

    @SerializedName("time")
    var time: List<time>,
)

@Parcelize
data class time(
    @SerializedName("startTime")
    var startTime: String,

    @SerializedName("endTime")
    var endTime: String,

    @SerializedName("parameter")
    var parameter: parameter,

    var tpye: Int = 0
) : Parcelable

@Parcelize
data class parameter(
    @SerializedName("parameterName")
    var parameterName: String,

    @SerializedName("parameterUnit")
    var parameterUnit: String
) : Parcelable



