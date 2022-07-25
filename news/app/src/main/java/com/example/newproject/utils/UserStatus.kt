package com.example.newproject.utils

enum class UserStatus(val id: Int, val desc: String, val creditStatus : String) {
    None(0,"н/д","Вы не можете подать заявку на кредит"),

    NotIdentified(1,"Не идентифицирован","Вы не можете подать заявку на кредит"),

    NotConfirmed(2,"Не подтвержден","Вы не можете подать заявку на кредит"),

    Identified(3,"Идентифицирован","Вы можете подать заявку на кредит")
}