package com.example.newproject.ui.api.models.references

data class Area(
    val id: Int,
    val name: String,
    val regionId: Int
){
    override fun toString(): String = name
}