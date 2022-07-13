package com.example.newproject.repository

import com.example.newproject.App
import com.example.newproject.ui.api.models.Otp
import com.example.newproject.ui.api.models.Register
import retrofit2.Response

class Repository {

    private val api = App.service!!

    suspend fun sendNumber(number: Register) : Response<String>{
        return api.registerNumber(number)
    }

    suspend fun sendOtp(number: Int, code: String) : Response<Otp> {
        return api.otpCode(number,code)
    }

    suspend fun takeText() :Response<String> {
        return api.getAgreementText()
    }

}