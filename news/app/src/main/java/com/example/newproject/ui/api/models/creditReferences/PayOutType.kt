package com.example.newproject.ui.api.models.creditReferences

data class PayOutType(
    val key: Int,
    val value: String
){
    override fun toString(): String = value
}