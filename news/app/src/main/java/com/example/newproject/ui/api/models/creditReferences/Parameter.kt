package com.example.newproject.ui.api.models.creditReferences

data class Parameter(
    val currencyId: Int,
    val incomeApproveTypeId: Int,
    val maxAmount: Int,
    val maxPeriod: Int,
    val maxRate: Int,
    val minAmount: Int,
    val minPeriod: Int,
    val minRate: Int,
    val mortgageTypeId: Int
)