package com.example.newproject.ui.api.models.references

data class City(
    val areaId: Int,
    val id: Int,
    val cityName: String,
    val regionId: Int
){
    override fun toString(): String = cityName
}