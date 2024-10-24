package com.example.veterinaria16.Domain

data class Customer(
    val Id: Int = 0,
    val Name : String = "",
    val CPF : String = "",
    val Telephone : String = "",
    val Email : String = "",
    val Senha : String = "",
    val mutableList: MutableList<Animal>?
)
