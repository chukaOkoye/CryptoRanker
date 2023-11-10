package com.example.coinstask.logging

import android.util.Log

interface Logger {
    fun d(msg: String)
}

object AndroidLogger : Logger {
    override fun d(msg: String) {
        Log.d("PKM", msg)
    }
}

object VoidLogger : Logger {
    override fun d(msg: String) {
        // Into the void it goes
        // In a production app, you may have more granular control over what is logged and when
    }
}