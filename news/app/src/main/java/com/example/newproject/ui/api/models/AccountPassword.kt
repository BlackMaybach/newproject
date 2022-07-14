package com.example.newproject.ui.api.models

data class AccountPassword(
    val currentPassword : String,
    val newPassword : String,
    val confirmNewPassword : String
)
