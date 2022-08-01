package com.example.newproject.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class SharedPreference(context: Context) {
    private val APP_PREF_NAME = "App"
    private val preference = context.getSharedPreferences(APP_PREF_NAME, Context.MODE_PRIVATE)

    var userToken: String?
        get() = preference.getString("TOKEN", null)
        set(value) = preference.edit().putString("TOKEN", value).apply()


}