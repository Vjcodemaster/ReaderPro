package com.vj.readerpro.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SMSData(
    var smsId: Long,
    var phoneNumber: String,
    var date: String,
    var time: String,
    var message : String,
    var person : String?,
    var timeDifference : String
) : Parcelable

data class TimeFrameData(var groupTimeFrame: String, var index : Int, var isSelected : Boolean)
