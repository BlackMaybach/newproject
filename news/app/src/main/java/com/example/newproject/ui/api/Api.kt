package com.example.newproject.ui.api

import com.example.newproject.ui.api.models.AccountLogin
import com.example.newproject.ui.api.models.AccountPassword
import com.example.newproject.ui.api.models.AccountRegister
import com.example.newproject.ui.api.models.LoginResponse
import com.example.newproject.ui.api.models.UserInfo.userInfo
import com.example.newproject.ui.api.models.creditCalculator.GetCalculation
import com.example.newproject.ui.api.models.creditCalculator.PostCalculation
import com.example.newproject.ui.api.models.creditReferences.CreditReferences
import com.example.newproject.ui.api.models.photo.PhotosResponse
import com.example.newproject.ui.api.models.references.getReferences
import retrofit2.Response
import retrofit2.http.*


interface Api {
    // отправка номера регистрация
    @POST("Account/Register")
    suspend fun registerNumber(@Body phoneNumber: AccountRegister): Response<String>

    // отправка номера и кода(смс)
    @POST("/Account/Login")
    suspend fun otpCode(@Body accountLogin : AccountLogin) : Response<LoginResponse>

    // логинизация
    @POST("/Account/Login")
    suspend fun loginInSystem(@Body accountLogin : AccountLogin) : Response<LoginResponse>

    // отправка пароля
    @POST("/Account/ChangePassword")
    suspend fun password(@Body accountPassword: AccountPassword) : Response<String>

    // получение списков
    @GET("/User/GetReferences")
    suspend fun getReferencesApi() : Response<getReferences>

    //получение данных клиента
    @GET("/User/GetCustomer")
    suspend fun getUserInfo() : Response<userInfo>


    //получение справочников для кредита
    @GET("/Loan/GetReferences")
    suspend fun getCreditInfo() : Response<CreditReferences>

    //отправка рассчета калькулятор
    @POST("/LoanCalculator/GetLoanSchedule")
    suspend fun getCalculationInfo(@Body postCalculation: PostCalculation) : Response<GetCalculation>

    // получить айди картинки
    @GET("/Image/GetImageIdsb")
    suspend fun getIdImage() : Response<PhotosResponse>

    // получить артинку
    @GET("/Image/GetDocument")
    suspend fun getPhotoById(
        @Query("documentId") id : Int
    ) : Response<PhotosResponse>

    //соглашение
    @GET("/Account/GetAgreement")
    suspend fun getAgreementText() : Response<String>


}