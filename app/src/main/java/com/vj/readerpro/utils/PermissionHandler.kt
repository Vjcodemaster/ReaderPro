package com.vj.readerpro.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat

object PermissionHandler {

    val permissionReadSMS = Manifest.permission.READ_SMS

    fun requestSMSPermission(activity: Activity){
            requestPermissions(activity, arrayOf(permissionReadSMS),
                EnumConstants.SMS_PERMISSION.ordinal
            )
    }

    fun permissionGranted(permission: String, activityContext: Activity): Boolean {
        return ContextCompat.checkSelfPermission(
                activityContext, permission) == PackageManager.PERMISSION_GRANTED
    }
}