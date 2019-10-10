package com.example.clerkapplication.util

import android.util.Log

object ErrorLogger {

    private const val ERROR_TAG = "ERROR_TAG"
    fun logError(throwable: Throwable   ){
        Log.e(ERROR_TAG,throwable.toString())
    }
}