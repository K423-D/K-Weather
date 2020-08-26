package com.k_weather.android

import android.app.Application
import android.content.Context
import android.widget.Toast

/**
 * 获取全局 context 的 KApplication 类，使用方式: KApplication.context
 */
class KApp : Application() {
    companion object {
        lateinit var context: Context
        const val TOKEN = "7M6UC8hyPvRKl3Qw"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}

fun String.showToast() {
    Toast.makeText(KApp.context, this, Toast.LENGTH_SHORT).show()
}