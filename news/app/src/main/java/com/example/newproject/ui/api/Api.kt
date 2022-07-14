package com.example.newproject.ui.api

import com.example.newproject.ui.api.models.AccountLogin
import com.example.newproject.ui.api.models.AccountPassword
import com.example.newproject.ui.api.models.AccountRegister
import com.example.newproject.ui.api.models.LoginResponse
import retrofit2.Response
import retrofit2.http.*


interface Api {
    // отправка номера регистрация
    @POST("Account/Register")
    suspend fun registerNumber(@Body phoneNumber: AccountRegister): Response<String>

    // отправка номера и кода(смс)
    @POST("/Account/Login")
    suspend fun otpCode(@Body accountLogin : AccountLogin) : Response<LoginResponse>

    // отправка пароля
    @POST("/Account/ChangePassword")
    suspend fun password(@Body accountPassword: AccountPassword) : Response<String>

    //соглашение
    @GET("/Account/GetAgreement")
    suspend fun getAgreementText() : Response<String>


}