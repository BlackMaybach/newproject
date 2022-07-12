package com.example.newproject.ui.api

import com.example.newproject.repository.Repository
import com.example.newproject.ui.api.models.Register
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path


interface Api {

    @POST("Account/Register")
    suspend fun registerNumber(@Body phoneNumber: Register): Response<String>

}