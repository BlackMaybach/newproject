package com.example.newproject.ui.api.models

data class Register(
    val inn : String?,
    val name : String?,
    val surname : String?,
    val patronymic : String?,
    val documentSeries : String?,
    val documentNo : String?,
    val phoneNumber : String,
    val email : String?,
    val login : String?
)
