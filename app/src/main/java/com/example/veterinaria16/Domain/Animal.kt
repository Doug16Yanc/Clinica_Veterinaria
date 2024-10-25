package com.example.veterinaria16.Domain

import com.example.veterinaria16.Domain.enums.Category
import com.example.veterinaria16.Domain.enums.SexAnimal

data class Animal(
    val IdCustomer : Int = 0,
    val Id : Int = 0,
    val Name : String = "",
    val weight : Double = 0.0,
    val birthday : String = "",
    val category : Category?,
    val sex : SexAnimal?
)