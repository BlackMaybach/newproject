package com.example.newproject.repository

import com.example.newproject.App
import com.example.newproject.ui.api.models.AccountLogin
import com.example.newproject.ui.api.models.AccountPassword
import com.example.newproject.ui.api.models.AccountRegister
import com.example.newproject.ui.api.models.LoginResponse
import retrofit2.Response

class Repository {

    private val api = App.service!!

    suspend fun sendNumber(number: AccountRegister) : Response<String>{
        return api.registerNumber(number)
    }

    suspend fun sendSms(account: AccountLogin) : Response<LoginResponse> {
        return api.otpCode(account)
    }

    suspend fun sendPassword(password: AccountPassword) : Response<String> {
        return api.password(password)
    }


    suspend fun takeText() :Response<String> {
        return api.getAgreementText()
    }

}