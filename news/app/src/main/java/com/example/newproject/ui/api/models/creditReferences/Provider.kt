package com.example.newproject.ui.api.models.creditReferences

data class Provider(
    val channelType: Int,
    val providerID: Int,
    val providerName: String
){
    override fun toString(): String = providerName
}