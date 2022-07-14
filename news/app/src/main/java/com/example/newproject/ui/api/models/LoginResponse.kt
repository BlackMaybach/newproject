package com.example.newproject.ui.api.models

data class LoginResponse(
    val accessToken: String,
    val expiredDate: String,
    val id: Int,
    val isPasswordChanged: Boolean,
    val message: String,
    val status: Int,
    val userName: String,
    val userRoles: List<String>
)
