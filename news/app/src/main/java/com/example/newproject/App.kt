package com.example.newproject

import android.app.Application
import com.example.newproject.ui.api.Api
import com.example.newproject.ui.api.RetrofitObject

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        service = RetrofitObject.getClient().create(Api::class.java)
        instance = this
    }
    companion object {
        var service: Api? = null
        var instance = App()
    }
}