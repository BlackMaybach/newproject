package com.example.newproject.ui.api.models.creditCalculator

data class PostCalculation(
    val discountPeriod: Int, // Льготный период
    val firstPaymentDate: String, // Дата первой выплаты
    val loanAmount: Int, // сумма
    val paymentType: Int, // тип платежа
    val percentsBaseType: Int, // процент
    val period: Int // срок
)