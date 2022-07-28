package com.example.newproject.utils

enum class PaymentType(val id: Int, val desc: String) {
    AnnuityPayments(1,"Аннуитетные платежи"),
    EqualPayments(2,"Платежи равными долями"),
    DailyPayments(5,"Ежедневные платежи");

    override fun toString() : String {
        return desc
    }

}