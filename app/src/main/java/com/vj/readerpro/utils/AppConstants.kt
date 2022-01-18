package com.vj.readerpro.utils

object AppConstants {
    enum class LoggedInMode(val type: Int) {
        MODE_LOGGED_OUT(0), MODE_LOGGED_IN(1);

    }
    const val SMS_DB_KEY_ID = "_id"
    const val SMS_DB_KEY_ADDRESS = "address"
    const val SMS_DB_KEY_DATE = "date"
    const val SMS_DB_KEY_BODY = "body"
    const val SMS_DB_KEY_PERSON = "person"
}