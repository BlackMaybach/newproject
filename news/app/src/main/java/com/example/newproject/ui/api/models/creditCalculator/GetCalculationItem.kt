package com.example.newproject.ui.api.models.creditCalculator

import android.os.Parcelable
import java.io.Serializable

data class GetCalculationItem(
    val amount: Double,
    val date: String,
    val days: Int,
    val deferredInterest: Int,
    val effectiveRate: Double,
    val interestRate: Int,
    val mainAmount: Double,
    val rate: Int,
    val remainMainDebt: Double,
    val tax: Double
) : Serializable