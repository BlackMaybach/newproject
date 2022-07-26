package com.example.newproject.ui.api.models.creditReferences

data class OfflineCredit(
    val parameters: List<Parameter>,
    val productId: Int,
    val purposeId: Int
)