package com.example.newproject.repository

import com.example.newproject.ui.api.RetrofitObject
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

class Repository {

    private val api = RetrofitObject.api

    suspend fun sendNumber(number: AccountRegister) : Response<String>{
        return api.registerNumber(number)
    }

    suspend fun sendSms(account: AccountLogin) : Response<LoginResponse> {
        return api.otpCode(account)
    }

    suspend fun sendPassword(password: AccountPassword) : Response<String> {
        return api.password(password)
    }

    suspend fun sendLogin(login: AccountLogin) : Response<LoginResponse> {
        return api.loginInSystem(login)
    }

    suspend fun takeReferences() : Response<getReferences> {
        return api.getReferencesApi()
    }

    suspend fun takeUserInfo() : Response<userInfo> {
        return api.getUserInfo()
    }

    suspend fun takeCreditReferences() : Response<CreditReferences> {
        return api.getCreditInfo()
    }

    suspend fun takeText() :Response<String> {
        return api.getAgreementText()
    }

    suspend fun getPhotosIds(): Response<PhotosResponse> {
        return api.getIdImage()
    }

    suspend fun getImageFromId(id: Int): Response<PhotosResponse> {
        return api.getPhotoById(id)
    }

    suspend fun sendCalculation(calculation: PostCalculation): Response<GetCalculation> {
        return api.getCalculationInfo(calculation)
    }

}